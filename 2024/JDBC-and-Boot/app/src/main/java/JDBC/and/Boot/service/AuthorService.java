package JDBC.and.Boot.service;

import JDBC.and.Boot.domain.Author;

import java.util.Collection;

public interface AuthorService {
    Collection<Author> findAllByNamePart(String namePart);
}
