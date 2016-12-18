package fr.enseirb.books.persistence.jpa;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.persistence.BookRepository;
import fr.enseirb.books.persistence.LibraryRepository;
import restx.factory.Alternative;
import restx.factory.When;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Alternative(to = LibraryRepository.class)
@When(name = "persistence", value = "jpa")
public class JpaLibraryRepository implements LibraryRepository {

    private EntityManagerFactory emf;
    private BookRepository bookRepository;

    public JpaLibraryRepository(EntityManagerFactory emf, BookRepository bookRepository) {
        this.emf = emf;
        this.bookRepository = bookRepository;
    }

    @Override
    public <L extends AbstractLibrary> L create(L library) {
    	 library.setId(UUID.randomUUID().toString());

         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             em.persist(library);
         } finally {
             em.getTransaction().commit();
             em.close();
         }

         return library;
    }

    @Override 
    public <L extends AbstractLibrary> L update(L library) {
    	EntityManager em = emf.createEntityManager();
    	// Vérifier que les book e L sont bien dans la BDD. Utiliser bookRepository.
         try {
             em.getTransaction().begin();
             em.merge(library);
         } finally {
             em.getTransaction().commit();
             em.close();
         }

         return library; 
    }

    @Override
    public Iterable<AbstractLibrary> find() {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            return em.createQuery("select l from AbstractLibrary", AbstractLibrary.class).getResultList();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public Optional<AbstractLibrary> findById(String id) {
        return null;
    }

    @Override
    public void delete(AbstractLibrary library) {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Book toDelete = em.find(Book.class, library.getId());
            if (toDelete != null) {
                em.remove(toDelete);
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}
