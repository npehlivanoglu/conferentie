package be.vdab.conferantie.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    private final JdbcTemplate template;

    public TicketRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int findBeschikbaarTickets() {
        var sql = """
                select beschikbaar from tickets
                """;
        return template.queryForObject(sql, Integer.class);
    }

    public int findAndLockBeschikbaarTickets() {
        var sql = """
                select beschikbaar from tickets
                for update
                """;
        return template.queryForObject(sql, Integer.class);
    }

    public void boekEenticket() {
        var sql = """
                update tickets
                set beschikbaar = beschikbaar -1;
                """;
        template.update(sql);
    }
}
