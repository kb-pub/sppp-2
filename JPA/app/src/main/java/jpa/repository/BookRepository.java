package jpa.repository;

import jpa.domain.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    Collection<Book> findAllWithoutAuthors();
    Collection<Book> findAll();
    Collection<Book> findAllByTitlePart(String part);
    Optional<Book> findById(long id);
    void insert(Book book);
    void update(Book book);
}
