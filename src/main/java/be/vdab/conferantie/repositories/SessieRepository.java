package be.vdab.conferantie.repositories;

import be.vdab.conferantie.domain.Sessie;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SessieRepository {
    private final JdbcTemplate template;

    public SessieRepository(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<Sessie> mapper = (result, rowNum) ->
            new Sessie(
                    result.getLong("id"),
                    result.getString("naam"),
                    result.getLong("dagId"),
                    result.getObject("uur", LocalTime.class),
                    result.getLong("sprekerid"),
                    result.getInt("interesses")
            );

    public List<Sessie> findAllByDagId(long id) {
        var sql = """
                select id,naam,dagId,uur,sprekerid,interesses
                from sessies where dagId = ? order by uur
                """;
        return template.query(sql, mapper, id);
    }

    public Optional<Sessie> findById(long id) {
        try {
            var sql = """
                    select id,naam,dagId,uur,sprekerid,interesses
                    from sessies where id = ?
                    """;
            return Optional.of(template.queryForObject(sql, mapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }

    }

    public Optional<Sessie> findAndLockById(long id) {
        try {
            var sql = """
                    select id,naam,dagId,uur,sprekerid,interesses
                    from sessies where id = ?
                    for update
                    """;
            return Optional.of(template.queryForObject(sql, mapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }

    }

    public void findSessieInteressantById(long id) {
        var sql = """
                update sessies
                set interesses = interesses + 1
                where id = ?
                """;
        template.update(sql,id);
    }
}
