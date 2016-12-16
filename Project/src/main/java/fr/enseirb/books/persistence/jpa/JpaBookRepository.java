package fr.enseirb.books.persistence.jpa;


import fr.enseirb.books.domain.Book;
import fr.enseirb.books.persistence.AuthorRepository;
import fr.enseirb.books.persistence.BookRepository;
import restx.factory.Alternative;
import restx.factory.When;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.common.base.Optional;

@Alternative(to = BookRepository.class)
@When(name = "persistence", value = "jpa")
public class JpaBookRepository implements BookRepository {

    private EntityManagerFactory emf;
    private AuthorRepository authorRepository;

    public JpaBookRepository(EntityManagerFactory emf, AuthorRepository authorRepository) {
        this.emf = emf;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book create(Book book) {
    	 book.setId(UUID.randomUUID().toString());
    	 // Vérifier que les auteurs de book sont bien dans la BDD. Utiliser authorRepository
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             em.persist(book);
         } finally {
             em.getTransaction().commit();
             em.close();
         }

         return book; }

    @Override
    public Book update(Book book) {
    	 EntityManager em = emf.createEntityManager();
    	// Vérifier que les auteurs de book sont bien dans la BDD. Utiliser authorRepository
    	// Vérifier que le book est bien dans la BDD. Utiliser bookRepository.
         try {
             em.getTransaction().begin();
             em.merge(book);
         } finally {
             em.getTransaction().commit();
             em.close();
         }

         return book;    }

    @Override
    public Iterable<Book> find() {
    	 EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             return em.createQuery("from Book b", Book.class).getResultList();
         } finally {
             em.getTransaction().commit();
             em.close();
         }
    }

    @Override
    public Optional<Book> findById(String id) {
    	 EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             return Optional.fromNullable(em.find(Book.class, id));
         } finally {
             em.getTransaction().commit();
             em.close();
         }
    }

    @Override
    public Iterable<String> findTitles() {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            return em.createQuery("select b.title from Book b", String.class).getResultList();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public Iterable<Book> findByAuthorLastName(String lastName) {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // A FINIR BOOK. ...
            return em.createQuery("select b from  Book where Book.", Book.class).getResultList();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public void delete(Book book) {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book toDelete = em.find(Book.class, book.getId());
            if (toDelete != null) {
                em.remove(toDelete);
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}
