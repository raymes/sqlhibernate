package fr.enseirb.books.persistence.jpa;


import fr.enseirb.books.domain.Author;
import fr.enseirb.books.persistence.AuthorRepository;
import restx.factory.Alternative;
import restx.factory.When;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Alternative(to = AuthorRepository.class)
@When(name = "persistence", value = "jpa")
public class JpaAuthorRepository implements AuthorRepository {

    private EntityManagerFactory emf;

    public JpaAuthorRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author create(Author author) {
    	 author.setId(UUID.randomUUID().toString());

         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             em.persist(author);
         } finally {
             em.getTransaction().commit();
             em.close();
         }

         return author;
    }

    @Override
    public Optional<Author> findById(String id) {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Author> authors = em.createQuery("from Company c where c.id = :id", Author.class)
                                    .setParameter("id", id)
                                    .getResultList();
            if (authors.isEmpty()) {
                return Optional.absent();
            } else {
                return Optional.fromNullable(authors.get(0));
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}