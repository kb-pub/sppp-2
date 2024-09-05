package JDBC.and.Boot.service;

import JDBC.and.Boot.domain.Book;

import java.util.Collection;

public interface BookService {
    Collection<Book> findAllWithoutAuthors();
    Collection<Book> findAll();
    Collection<Book> findAllByTitlePart(String part);
    void save(Book book);
}
