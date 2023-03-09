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
@Sql({"/dagen.sql", "/sprekers.sql", "/sessies.sql"})
@AutoConfigureMockMvc
class DagControllersTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String DAGEN = "dagen";
    private final static String SESSIES = "sessies";
    private final MockMvc mockMvc;

    DagControllersTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private long idVanTestDag() {
        return jdbcTemplate.queryForObject("select id from dagen where datum = '2024-01-01'", Long.class);
    }

    private long idVanTestSessie() {
        return jdbcTemplate.queryForObject("select id from sessies where naam = 'test1'", Long.class);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/dagen"))
                .andExpectAll(status().isOk(),
                        jsonPath("length()").value(countRowsInTable(DAGEN)));
    }

    @Test
    void findAllSessiesByDagID() throws Exception {
        var id = idVanTestDag();
        mockMvc.perform(get("/dagen/" + id + "/sessies")).andExpectAll(
                status().isOk(),
                jsonPath("length()").value(countRowsInTableWhere(SESSIES, "dagId = " + id)));

    }

    @Test
    void findSessieById() throws Exception {
        var id = idVanTestDag();
        var sessieId = idVanTestSessie();
        mockMvc.perform(get("/dagen/sessies/" + sessieId))
                .andExpectAll(status().isOk(),
                        jsonPath("naam")
                                .value("test1"));
    }
}