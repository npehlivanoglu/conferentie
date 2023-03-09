package be.vdab.conferantie.repositories;

import be.vdab.conferantie.domain.Dag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DagRepository {
    private final JdbcTemplate template;

    public DagRepository(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<Dag> mapper = (result, rowNum) ->
            new Dag(result.getLong("id"),
                    result.getObject("datum", LocalDate.class));
    public List<Dag> findAll() {
        var sql = """
                select id,datum
                from dagen
                order by datum
                """;
        return template.query(sql,mapper);
    }
}
