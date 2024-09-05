package app.service;

import app.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(long id);
    void insert(Book book);
}
