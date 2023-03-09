package be.vdab.conferantie.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql({"/dagen.sql", "/sprekers.sql"})
@AutoConfigureMockMvc
class SprekerControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String SPREKERS = "sprekers";
    private final MockMvc mockMvc;

    SprekerControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private long idVanTestSpreker() {
        return jdbcTemplate.queryForObject("select id from sprekers where voornaam = 'testVoornaam'", long.class);
    }

    @Test
    void findById() throws Exception {
        var id = idVanTestSpreker();
        mockMvc.perform(get("/sprekers/" + id)).andExpectAll(
                status().isOk(),
                jsonPath("voornaam").value("testVoornaam"));
    }
}