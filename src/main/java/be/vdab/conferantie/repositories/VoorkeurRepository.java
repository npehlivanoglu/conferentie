package be.vdab.conferantie.repositories;

import be.vdab.conferantie.dto.DeelnemerIdSesssieId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class VoorkeurRepository {
    private final JdbcTemplate template;

    public VoorkeurRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void create(DeelnemerIdSesssieId deelnemerIdSesssieId) {
        var sql = """
                insert into deelnemervoorkeursessies(deelnemerId,sessieId)
                values(?,?)
                """;
        template.update(sql, deelnemerIdSesssieId.deelnemerId(), deelnemerIdSesssieId.sessieId());
    }
}
