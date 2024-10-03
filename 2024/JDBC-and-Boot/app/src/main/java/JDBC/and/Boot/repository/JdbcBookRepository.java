package JDBC.and.Boot.repository;

import JDBC.and.Boot.domain.Author;
import JDBC.and.Boot.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {
    private final static RowMapper<Book> MAPPER = (rs, num) ->
            new Book(rs.getLong("id"),
                    rs.getString("title"),
                    rs.getInt("publish_year"),
                    null);

    private final static ResultSetExtractor<List<Book>> EXTRACTOR = rs -> {
        var books = new ArrayList<Book>();
        var authors = new HashMap<Long, Author>();
        var currentBook = new Book(0, null, 0, null);
        while (rs.next()) {
            var bookId = rs.getLong("b_id");
            if (currentBook.getId() != bookId) {
                currentBook = new Book(bookId, rs.getString("b_title"),
                        rs.getInt("b_publish_year"), new HashSet<>());
                books.add(currentBook);
            }

            var authorId = rs.getLong("a_id");
            if (authorId == 0)
                throw new RuntimeException("no author for book " + currentBook.getId());
            var author = authors.get(authorId);
            if (author == null) {
                var authorName = rs.getString("a_name");
                author = new Author(authorId, authorName, new HashSet<>());
                authors.put(authorId, author);
            }
            author.getBooks().add(currentBook);

            currentBook.getAuthors().add(author);
        }
        return books;
    };

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Collection<Book> findAllWithoutAuthors() {
        return jdbc.query("select id, title, publish_year from books", MAPPER);
    }

    @Override
    public Collection<Book> findAll() {
        return jdbc.query("""
                        select
                            b.id b_id,
                            b.title b_title,
                            b.publish_year b_publish_year,
                            a.id a_id,
                            a.name a_name
                        from books b left join books_authors ba on b.id = ba.book_id
                            left join authors a on ba.author_id = a.id
                        order by b.id, a.id
                        """,
                EXTRACTOR);
    }

    @Override
    public Collection<Book> findAllByTitlePart(String part) {
        return jdbc.query("""
                        select
                            b.id b_id,
                            b.title b_title,
                            b.publish_year b_publish_year,
                            a.id a_id,
                            a.name a_name
                        from books b left join books_authors ba on b.id = ba.book_id
                            left join authors a on ba.author_id = a.id
                        where lower(title) like :titlePart
                        order by b.id, a.id
                        """,
                Map.of("titlePart", "%" + part.strip().toLowerCase() + "%"),
                EXTRACTOR);
    }

    @Override
    public Optional<Book> findById(long id) {
        var list = jdbc.query("""
                select
                    b.id b_id,
                    b.title b_title,
                    b.publish_year b_publish_year,
                    a.id a_id,
                    a.name a_name
                from books b left join books_authors ba on b.id = ba.book_id
                    left join authors a on ba.author_id = a.id
                where b.id = :id
                """,
                Map.of("id", id),
                EXTRACTOR);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(list.get(0));
        }
    }

    @Override
    public void insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("""
                        insert into books (title, publish_year)
                        values (:title, :publish_year)
                        """,
                SqlParams.of("title", book.getTitle(),
                        "publish_year", book.getYear()),
                keyHolder);
        book.setId((Long)(keyHolder.getKeys().get("id")));
        insertAuthors(book);
    }

    public static class SqlParams {
        public static MapSqlParameterSource of(String k1, Object v1) {
            var params = new MapSqlParameterSource();
            params.addValue(k1, v1);
            return params;
        }

        public static MapSqlParameterSource of(String k1, Object v1, String k2, Object v2) {
            var params = new MapSqlParameterSource();
            params.addValue(k1, v1);
            params.addValue(k2, v2);
            return params;
        }
    }

    @Override
    public void update(Book book) {
        var params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("publish_year", book.getYear());
        params.addValue("id", book.getId());
        jdbc.update("""
                        update books set title = :title, publish_year = :publish_year
                        where id = :id
                        """,
                params);
        deleteAuthors(book);
        insertAuthors(book);
    }

    private void insertAuthors(Book book) {
        if (book.getAuthors().size() > 0) {
            var paramArray = book.getAuthors().stream()
                    .map(author -> {
                        var params = new MapSqlParameterSource();
                        params.addValue("book_id", book.getId());
                        params.addValue("author_id", author.getId());
                        return params;
                    }).toArray(MapSqlParameterSource[]::new);
            jdbc.batchUpdate("insert into books_authors values(:book_id, :author_id)",
                    paramArray);
        }
    }

    private void deleteAuthors(Book book) {
        jdbc.update("delete from books_authors where book_id = :id",
                Map.of("id", book.getId()));
    }
}
