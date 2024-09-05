package jpa.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "BooksWithAuthors",
                attributeNodes = @NamedAttributeNode(value = "authors")
        ),
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(name = "publish_year")
    private int year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @BatchSize(size = 10)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Author> authors;
}
