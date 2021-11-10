package app.service;

import app.domain.Author;
import app.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StubBookService implements BookService {
    private final List<Book> books = new ArrayList<>(List.of(
            new Book(1, "book 1", List.of(new Author(1, "author 1"))),
            new Book(2, "book 2", List.of(
                    new Author(2, "author 2"),
                    new Author(3, "author 3"))),
            new Book(3, "book 3", List.of(
                    new Author(4, "author 4"),
                    new Author(5, "author 5"),
                    new Author(6, "author 6")))
    ));

    @Override
    public Optional<Book> findById(long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void insert(Book book) {
        if (findById(book.getId()).isEmpty()) {
            books.add(book);
        }
        else {
            throw new EntityAlreadyExistException("there is book with id " + book.getId());
        }
    }
}
