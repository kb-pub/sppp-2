package JDBC.and.Boot.domain;

import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Author {
    private long id;
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Collection<Book> books;
}
