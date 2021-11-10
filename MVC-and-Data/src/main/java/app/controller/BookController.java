package app.controller;

import app.domain.Book;
import app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/{id:\\d+}")
    public Book findById(@PathVariable long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no book found with id: " + id));
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping("/book/new")
    public ResponseEntity<String> newBook(@RequestBody Book book) {
        bookService.insert(book);
        return ResponseEntity.status(201).build();
    }

}
