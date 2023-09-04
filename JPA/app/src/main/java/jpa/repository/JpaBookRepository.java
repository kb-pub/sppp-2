package jpa.repository;

import jpa.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Book> findAllWithoutAuthors() {
        return em.createQuery("""
                        select b from Book b
                        """, Book.class)
                .getResultList();
    }

    @Override
    public Collection<Book> findAll() {
        return em.createQuery("""
                        select b from Book b
                        """, Book.class)
                .setHint("javax.persistence.loadgraph",
                        em.getEntityGraph("BooksWithAuthors"))
                .getResultList();
    }

    @Override
    public Collection<Book> findAllByTitlePart(String part) {
        return em.createQuery("""
                        select b from Book b where b.title like :titlePattern
                        """, Book.class)
                .setHint("javax.persistence.loadgraph",
                        em.getEntityGraph("BooksWithAuthors"))
                .setParameter("titlePattern", "%" + part + "%")
                .getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(
                Book.class, id, LockModeType.NONE,
                Map.of("javax.persistence.loadgraph",
                        em.getEntityGraph("BooksWithAuthors"))));
    }

    @Override
    @Transactional
    public void insert(Book book) {
        assert book.getId() == 0;
        em.persist(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        assert book.getId() != 0;
        em.merge(book);
    }
}
