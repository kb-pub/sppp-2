package app.domain;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private final long id;
    private final String title;
    private final List<Author> authors;
}
