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

    public long create(DeelnemerIdSesssieId deelnemerIdSesssieId) {
        var sql = """
                insert into deelnemervoorkeursessies(deelnemerId,sessieId)
                values(?,?)
                """;
        var keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            var statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, deelnemerIdSesssieId.deelnemerId());
            statement.setLong(2, deelnemerIdSesssieId.sessieId());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
