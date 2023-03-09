package be.vdab.conferantie.repositories;

import be.vdab.conferantie.domain.Deelnemer;
import be.vdab.conferantie.dto.VoornaamFamilienaam;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class DeelnemerRepository {
    private final JdbcTemplate template;

    public DeelnemerRepository(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<Deelnemer> mapper = (result, rowNum) ->
            new Deelnemer(result.getLong("id"),
                    result.getString("voornaam"),
                    result.getString("familienaam"),
                    result.getString("email"));

    public long create(Deelnemer deelnemer) {
        var sql = """
                insert into deelnemers(voornaam,familienaam,email)
                values(?,?,?)
                """;
        var keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            var statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, deelnemer.getVoornaam());
            statement.setString(2, deelnemer.getFamilienaam());
            statement.setString(3, deelnemer.getEmail());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Optional<Deelnemer> findAndLockByVoornaamEnFamilienaam(VoornaamFamilienaam voornaamFamilienaam) {
        try {
            var sql = """
                    select id,voornaam,familienaam,email
                    from deelnemers
                    where voornaam = ? and familienaam = ?
                    for update
                    """;
            return Optional.of(template.queryForObject(sql, mapper, voornaamFamilienaam.voornaam(), voornaamFamilienaam.familienaam()));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }


}
