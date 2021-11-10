package jpa.service;

import jpa.domain.Author;

import java.util.Collection;

public interface AuthorService {
    Collection<Author> findAllByNamePart(String namePart);
}
