package be.vdab.conferantie.repositories;

import be.vdab.conferantie.domain.Spreker;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SprekerRepository {
    private final JdbcTemplate template;

    public SprekerRepository(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<Spreker> mapper = (result, rowNum) -> new Spreker(
            result.getLong("id"),
            result.getString("voornaam"),
            result.getString("familienaam"),
            result.getString("titel"),
            result.getString("firma")
    );

    public Optional<Spreker> findById(long id) {
        try {
            var sql = """
                    select id,voornaam,familienaam,titel,firma
                    from sprekers
                    where id = ?
                    """;
            return Optional.of(template.queryForObject(sql, mapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }

    }
}
