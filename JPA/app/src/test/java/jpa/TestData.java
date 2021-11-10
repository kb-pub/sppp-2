package jpa;

import jpa.domain.Author;
import jpa.domain.Book;
import java.util.HashSet;
import java.util.List;

public class TestData {

    public long bookNextId = 1;
    public Book bookWA1 = new Book(bookNextId++, "Book 1", 2015, null);
    public Book bookWA2 = new Book(bookNextId++, "Book 2", 2016, null);
    public Book bookWA3 = new Book(bookNextId++, "Книга 3", 2017, null);
    public Book bookWABeforeUpdate = new Book(bookNextId++, "Book before update", 2033, null);
    public List<Book> booksWA = List.of(bookWA1, bookWA2, bookWA3, bookWABeforeUpdate);

    public Book book1 = bookWA1.toBuilder().authors(new HashSet<>()).build();
    public Book book2 = bookWA2.toBuilder().authors(new HashSet<>()).build();
    public Book book3 = bookWA3.toBuilder().authors(new HashSet<>()).build();
    public Book bookBeforeUpdate = bookWABeforeUpdate.toBuilder().authors(new HashSet<>()).build();
    public List<Book> books = List.of(book1, book2, book3, bookBeforeUpdate);
    public Book bookForInsert = new Book(0, "Book for insert", 2055, new HashSet<>());
    public Book bookAfterUpdate = new Book(bookBeforeUpdate.getId(),
            "Book after update", 2044, new HashSet<>());

    public long authorNextId = 1;
    public Author authorWB1 = new Author(authorNextId++, "Author 1", null);
    public Author authorWB2 = new Author(authorNextId++, "Author 2", null);
    public Author authorWB3 = new Author(authorNextId++, "Автор 3", null);
    public Author authorWB4 = new Author(authorNextId++, "Автор 4", null);
    public List<Author> authorsWB = List.of(authorWB1, authorWB2, authorWB3, authorWB4);

    public Author author1 = authorWB1.toBuilder().books(new HashSet<>()).build();
    public Author author2 = authorWB2.toBuilder().books(new HashSet<>()).build();
    public Author author3 = authorWB3.toBuilder().books(new HashSet<>()).build();
    public Author author4 = authorWB4.toBuilder().books(new HashSet<>()).build();
    public List<Author> authors = List.of(author1, author2, author3, author4);

    {
        book1.getAuthors().add(author1);
        book2.getAuthors().add(author2);
        book2.getAuthors().add(author3);
        book3.getAuthors().add(author3);
        book3.getAuthors().add(author4);

        bookForInsert.getAuthors().add(author2);
        bookForInsert.getAuthors().add(author4);

        bookBeforeUpdate.getAuthors().add(author1);
        bookBeforeUpdate.getAuthors().add(author3);

        bookAfterUpdate.getAuthors().add(author2);
        bookAfterUpdate.getAuthors().add(author4);
    }
}