package jpa.repository;

import jpa.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static java.util.stream.Collectors.*;

@JdbcTest
@Import(JdbcAuthorRepository.class)
class JdbcAuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    TestData data;

    @BeforeEach
    void setUp() {
        data = new TestData();
    }

    @Test
    void findAllWithoutBooksByNamePart() {
        Assertions.assertThat(authorRepository.findAllWithoutBooksByNamePart("THOR"))
                .containsExactlyInAnyOrderElementsOf(data.authorsWB.stream()
                        .filter(author -> author.getName().toLowerCase().contains("thor"))
                        .collect(toList()));
    }
}