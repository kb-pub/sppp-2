package JDBC.and.Boot.ui;

import JDBC.and.Boot.AppException;
import JDBC.and.Boot.domain.Author;
import JDBC.and.Boot.domain.Book;
import JDBC.and.Boot.service.AuthorService;
import JDBC.and.Boot.service.BookService;
import JDBC.and.Boot.service.CurrentLocaleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Collection;
import java.util.HashSet;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class Commands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final IO io;
    private final CurrentLocaleService currentLocaleService;

    public enum State {
        MAIN_MENU("main menu"), PROCESSING_BOOK("book processing");
        @Getter
        private final String title;

        State(String title) {
            this.title = title;
        }
    }

    @Getter
    private State state = State.MAIN_MENU;

    private Book handlingBook;

    @ShellMethod(value = "change current language", key = {"language", "lang"})
    @ShellMethodAvailability("availableInMainMenu")
    public void setLanguage(String lang) {
        try {
            currentLocaleService.set(lang.strip().toLowerCase());
        }
        catch (AppException e) {
            io.interPrintln(e.getMessage(), e.getParams());
        }
    }

    @ShellMethod(value = "show all books", key = "book-all")
    @ShellMethodAvailability("availableInMainMenu")
    public void showAllBooks() {
        var books = bookService.findAll();
        if (books.isEmpty())
            io.interPrintln("no-book-found");
        else
            io.println(booksToString(books));
    }

    @ShellMethod(value = "find book by title part", key = "book-find")
    @ShellMethodAvailability("availableInMainMenu")
    public void findBookByTitlePart(@ShellOption(defaultValue = "") String titlePart) {
        if (titlePart.isEmpty()) {
            io.interPrint("print-title-part");
            titlePart = io.readLine();
        }
        if (titlePart.isBlank())
            io.interPrintln("operation-cancelled-by-empty-line");
        else {
            var books = bookService.findAllByTitlePart(titlePart);
            if (books.isEmpty())
                io.interPrintln("no-book-found");
            else
                io.println(booksToString(books));
        }
    }

    @ShellMethod(value = "update book", key = "book-update")
    @ShellMethodAvailability("availableInMainMenu")
    public void updateBook(@ShellOption(defaultValue = "") String titlePart) {
        if (titlePart.isEmpty()) {
            io.interPrint("print-title-part");
            titlePart = io.readLine();
        }
        if (titlePart.isBlank())
            io.interPrintln("operation-cancelled-by-empty-line");
        else {
            var books = bookService.findAllByTitlePart(titlePart);
            if (books.isEmpty())
                io.interPrintln("no-book-found");
            else if (books.size() > 1) {
                io.interPrintln("too-many-books-found");
                io.println(booksToString(books));
            } else {
                handlingBook = books.iterator().next();
                state = State.PROCESSING_BOOK;
                show();
            }
        }
    }

    @ShellMethod(value = "insert new book", key = "book-insert")
    @ShellMethodAvailability("availableInMainMenu")
    public void insertBook() {
        handlingBook = new Book(0, "", 0, new HashSet<>());
        state = State.PROCESSING_BOOK;
        show();
    }

    @ShellMethod(value = "show handling book", key = "show")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void show() {
        io.println(bookToString(handlingBook));
    }

    @ShellMethod(value = "set book's title", key = "set-title")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void setTitle(@ShellOption(defaultValue = "") String title) {
        if (title.isBlank()) {
            io.interPrint("set-title");
            title = io.readLine();
        }
        if (title.isBlank()) {
            io.interPrintln("Empty line entered, operation cancelled");
        } else {
            handlingBook.setTitle(title);
            io.interPrintln("new-title-is", handlingBook.getTitle());
        }
    }

    @ShellMethod(value = "set book's publish year", key = "set-year")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void setYear(@ShellOption(defaultValue = "") String year) {
        if (year.isBlank()) {
            io.interPrint("set-year");
            year = io.readLine();
        }
        if (year.isBlank()) {
            io.interPrintln("operation-cancelled-by-empty-line");
        } else if (!year.matches("^\\d+$")) {
            io.interPrintln("year-must-contain-digits-only");
        } else {
            int yearNumber = Integer.parseInt(year);
            handlingBook.setYear(yearNumber);
            io.interPrintln("new-year-is", handlingBook.getYear());
        }
    }

    @ShellMethod(value = "add an author", key = "add-author")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void addAuthor(@ShellOption(defaultValue = "") String authorNamePart) {
        if (authorNamePart.isBlank()) {
            io.interPrintln("enter-author-name-part");
            authorNamePart = io.readLine();
        }
        if (authorNamePart.isBlank()) {
            io.interPrintln("operation-cancelled-by-empty-line");
        } else {
            var authors = authorService.findAllByNamePart(authorNamePart);
            if (authors.isEmpty()) {
                io.interPrintln("no-author-found");
            } else if (authors.size() > 1) {
                io.interPrintln("several-authors-found");
                io.println(authorsToString(authors));
            } else {
                var author = authors.iterator().next();
                io.interPrintln("author-added");
                handlingBook.getAuthors().add(author);
                io.interPrintln("current-authors");
                io.println(authorsToString(handlingBook.getAuthors()));
            }
        }
    }

    @ShellMethod(value = "remove an author", key = "remove-author")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void removeAuthor(@ShellOption(defaultValue = "") String authorNamePart) {
        if (authorNamePart.isBlank()) {
            io.interPrint("enter-author-name-part");
            authorNamePart = io.readLine();
        }
        if (authorNamePart.isBlank()) {
            io.interPrintln("operation-cancelled-by-empty-line");
        } else {
            var authors = authorService.findAllByNamePart(authorNamePart);
            if (authors.isEmpty()) {
                io.interPrintln("no-author-found");
            } else if (authors.size() > 1) {
                io.interPrintln("several-authors-found");
                io.println(authorsToString(authors));
            } else {
                var author = authors.iterator().next();
                io.interPrintln("author-removed");
                handlingBook.getAuthors().remove(author);
                io.interPrintln("current-authors");
                io.println(authorsToString(handlingBook.getAuthors()));
            }
        }
    }

    @ShellMethod(value = "cancel current operation", key = "cancel")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void cancel() {
        io.interPrintln("operation-cancelled");
        state = State.MAIN_MENU;
    }

    @ShellMethod(value = "perform current operation", key = "perform")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void perform() {
        try {
            if (checkHandlingBook()) {
                bookService.save(handlingBook);
                io.interPrintln("operation-successful");
                state = State.MAIN_MENU;
            }
        } catch (AppException e) {
            io.interPrintln(e.getMessage(), e.getParams());
        }
    }

    private boolean checkHandlingBook() {
        if (handlingBook.getTitle().isBlank()) {
            throw new AppException("book.check.title-must-not-be-empty");
        }
        if (handlingBook.getYear() == 0) {
            throw new AppException("book.check.year-must-be-set");
        }
        if (handlingBook.getAuthors().isEmpty()) {
            throw new AppException("book.check.book-must-contains-at-least-one-author");
        }
        return true;
    }

    private Availability availableInMainMenu() {
        return state == State.MAIN_MENU ? Availability.available()
                : Availability.unavailable("available in " + State.MAIN_MENU.getTitle() +
                " only, you now in " + state.getTitle());
    }

    private Availability availableInUpdatingBook() {
        return state == State.PROCESSING_BOOK ? Availability.available()
                : Availability.unavailable("available in " + State.PROCESSING_BOOK.getTitle() +
                " only, you now in " + state.getTitle());
    }

    private String authorsToString(Collection<Author> authors) {
        return authors.stream().map(Author::getName).collect(joining("\n"));
    }

    private String booksToString(Collection<Book> books) {
        return books.stream()
                .map(this::bookToString)
                .collect(joining("====================\n"));
    }

    private String bookToString(Book book) {
        var empty = "<" + io.inter("book.empty") + ">";
        var title = book.getTitle().isBlank() ? empty : book.getTitle();
        var authors = book.getAuthors().isEmpty() ? empty :
                book.getAuthors().stream()
                        .map(Author::getName).collect(joining(", "));
        var year = book.getYear() == 0 ? empty : Integer.toString(book.getYear());

        return io.inter("book.title") + ": " + title + "\n" +
                io.inter("book.authors") + ": " + authors + "\n" +
                io.inter("book.year") + ": " + year + "\n";
    }
}
