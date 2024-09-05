package JDBC.and.Boot.repository;

import JDBC.and.Boot.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Collection<Author> findAllWithoutBooksByNamePart(String namePart) {
        return jdbc.query("""
                select id, name from authors where lower(name) like :namePart
                """,
                Map.of("namePart", "%" + namePart.strip().toLowerCase() + "%"),
                (rs, i) -> new Author(
                        rs.getLong("id"),
                        rs.getString("name"),
                        null));
    }
}
