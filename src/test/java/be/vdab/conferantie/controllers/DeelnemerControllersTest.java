package be.vdab.conferantie.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@Sql({"/tickets.sql", "/dagen.sql", "/sprekers.sql", "/sessies.sql", "/deelnemers.sql", "/deelnemervoorkeursessies.sql"})
@AutoConfigureMockMvc
class DeelnemerControllersTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static Path TEST_RESOURCES = Path.of("src/test/resources");
    private final static String TICKETS = "tickets";
    private final static String DAGEN = "dagen";
    private final static String SESSIES = "sessies";
    private final static String DEELNEMERS = "deelnemers";
    private final static String DEELNEMERVOORKEURSESSIES = "deelnemervoorkeursessies";
    private final MockMvc mockMvc;

    private long idVanTestDag() {
        return jdbcTemplate.queryForObject("select id from dagen where datum = '2024-01-01'", Long.class);
    }

    private long idVanTestSessie() {
        return jdbcTemplate.queryForObject("select id from sessies where naam = 'test1'", Long.class);
    }

    private long idVanTestSpreker() {
        return jdbcTemplate.queryForObject("select id from sprekers where voornaam = 'testVoornaam'", long.class);
    }

    DeelnemerControllersTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void create() throws Exception {
        var jsonData = Files.readString(TEST_RESOURCES.resolve("correcteBoeking.json"));
        jsonData = vervangData(jsonData);
        var id = mockMvc.perform(post("/deelnemers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(countRowsInTableWhere(DEELNEMERS, "voornaam = 'nieuweTestVoornaam'")).isOne();
        assertThat(countRowsInTableWhere(DEELNEMERVOORKEURSESSIES, "deelnemerId =" + id + " and sessieId = " + idVanTestSessie())).isOne();
        assertThat(countRowsInTableWhere(SESSIES, "id = " + idVanTestSessie() + " and interesses = 1")).isOne();
        assertThat(jdbcTemplate.queryForObject("select beschikbaar from tickets", long.class)).isEqualTo(99);

    }

    @ParameterizedTest
    @ValueSource(strings = {"boekingMetLegeVoornaam.json", "boekingMetLegeFamilienaam.json", "boekingMetLegeEmail.json"})
    void createMetVerkeerdeDataMislukt(String bestandsNaam) throws Exception {
        var jsonData = Files.readString(TEST_RESOURCES.resolve(bestandsNaam));
        jsonData = vervangData(jsonData);
        mockMvc.perform(post("/deelnemers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isBadRequest());
    }

    private String vervangData(String jsonData) {
        jsonData = jsonData.replaceAll("6666", String.valueOf(idVanTestDag()));
        jsonData = jsonData.replaceAll("7777", String.valueOf(idVanTestSpreker()));
        jsonData = jsonData.replaceAll("8888", String.valueOf(idVanTestSessie()));
        return jsonData;
    }

}