package JDBC.and.Boot.domain;

import lombok.*;

import java.util.Collection;
import java.util.Objects;

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
