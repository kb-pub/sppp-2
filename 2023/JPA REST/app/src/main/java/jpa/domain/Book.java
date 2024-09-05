package jpa.domain;

import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {
    private long id;
    private String title;
    private int year;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Author> authors;
}
