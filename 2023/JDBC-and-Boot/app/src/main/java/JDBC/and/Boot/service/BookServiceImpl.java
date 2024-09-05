package JDBC.and.Boot.service;

import JDBC.and.Boot.domain.Book;
import JDBC.and.Boot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Collection<Book> findAllWithoutAuthors() {
        return bookRepository.findAllWithoutAuthors();
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Collection<Book> findAllByTitlePart(String part) {
        return bookRepository.findAllByTitlePart(part);
    }

    @Override
    public void save(Book book) {
        if (book.getId() == 0)
            bookRepository.insert(book);
        else
            bookRepository.update(book);
    }
}
