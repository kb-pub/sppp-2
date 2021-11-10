package jpa.repository;

import jpa.domain.Author;

import java.util.Collection;

public interface AuthorRepository {
    Collection<Author> findAllWithoutBooksByNamePart(String namePart);
}
