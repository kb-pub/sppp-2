package JDBC.and.Boot.repository;

import JDBC.and.Boot.domain.Author;

import java.util.Collection;

public interface AuthorRepository {
    Collection<Author> findAllWithoutBooksByNamePart(String namePart);
}
