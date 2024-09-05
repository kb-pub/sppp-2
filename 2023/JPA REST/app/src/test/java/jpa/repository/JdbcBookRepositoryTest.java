package jpa.repository;

import jpa.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcBookRepository.class)
class JdbcBookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    TestData data;

    @BeforeEach
    void setUp() {
        data = new TestData();
    }

    @Test
    void findAllWithoutAuthors() {
        assertThat(bookRepository.findAllWithoutAuthors())
                .hasSameElementsAs(data.booksWA);
    }

    @Test
    void findAll() {
        assertThat(bookRepository.findAll())
                .hasSameElementsAs(data.books);
    }

    @Test
    void findAllByTitlePart() {
        assertThat(bookRepository.findAllByTitlePart("OOK"))
                .hasSameElementsAs(data.books.stream()
                        .filter(book -> book.getTitle().toLowerCase().contains("ook"))
                        .collect(toList()));
    }

    @Test
    void givenExistId_whenFindById_thenSuccess() {
        assertThat(bookRepository.findById(data.book3.getId()))
                .isPresent()
                .get()
                .isEqualTo(data.book3);
    }

    @Test
    void givenUnknownId_whenFindById_thenEmpty() {
        assertThat(bookRepository.findById(data.bookNextId))
                .isNotPresent();
    }

    @Test
    void insert() {
        bookRepository.insert(data.bookForInsert);
        var insertedBookOpt = bookRepository
                .findById(data.bookNextId);
        assertThat(insertedBookOpt)
                .isPresent()
                .get()
                .isEqualTo(data.bookForInsert);
        assertThat(insertedBookOpt.get().getAuthors())
                .containsExactlyInAnyOrderElementsOf(data.bookForInsert.getAuthors());
    }

    @Test
    void update() {
        bookRepository.update(data.bookAfterUpdate);
        var updatedBookOpt = bookRepository
                .findById(data.bookAfterUpdate.getId());
        assertThat(updatedBookOpt)
                .isPresent()
                .get()
                .isEqualTo(data.bookAfterUpdate);
        assertThat(updatedBookOpt.get().getAuthors())
                .containsExactlyInAnyOrderElementsOf(data.bookAfterUpdate.getAuthors());
    }
}