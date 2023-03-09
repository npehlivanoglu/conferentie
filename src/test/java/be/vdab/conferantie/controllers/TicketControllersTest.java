package be.vdab.conferantie.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllersTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String TICKETS = "tickets";
    private final MockMvc mockMvc;

    TicketControllersTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void findBeschikbaarTickets() throws Exception {
        mockMvc.perform(get("/tickets")).andExpectAll(
                status().isOk(),
                jsonPath("$")
                        .value(jdbcTemplate.queryForObject("select beschikbaar from tickets", long.class)));
    }
}