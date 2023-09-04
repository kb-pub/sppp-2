package jpa.repository;

import jpa.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class JpaAuthorRepository implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Author> findAllWithoutBooksByNamePart(String namePart) {
        return em.createQuery("""
            select a from Author a where lower(a.name) like :namePart
            """, Author.class)
                .setParameter("namePart", "%" + namePart + "%")
                .getResultList();
    }
}
