package fr.enseirb.books.persistence.jpa;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.persistence.BookRepository;
import fr.enseirb.books.persistence.LibraryRepository;
import restx.factory.Alternative;
import restx.factory.When;

import java.util.HashSet;
import java.util.Set;
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
    	 
    	 Set<Book> books = library.getBooks();
    	 // Set of books treated : with correct ids
    	 Set<Book> treatmentBooks = new HashSet<Book>();
    	 
    	 // Check if books exist in database
    	 for(Book book : books) {
	    	 // Book isn't present in database : we create it
	    	 if(!bookRepository.findById(book.getId()).isPresent()) {
	    		 book = bookRepository.create(book);
	    	 }
    		 treatmentBooks.add(book);
    	 }
    	 // Library's book is updated, to have the correct ids
    	 library.setBooks(treatmentBooks);

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

    	Set<Book> books = library.getBooks();
	   	// Set of books treated : with correct ids
	   	Set<Book> treatmentBooks = new HashSet<Book>();
	   	 
	   	// Check if books exist in database
	   	for(Book book : books) {
		   	 // Book isn't present in database : we create it
		   	 if(!bookRepository.findById(book.getId()).isPresent()) {
		   		 book = bookRepository.create(book);
		   	 }
	   	 treatmentBooks.add(book);
	  	}
	   	// Library's book is updated, to have the correct ids
	   	library.setBooks(treatmentBooks);
   	 
   	 
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
            return em.createQuery("From AbstractLibrary l", AbstractLibrary.class).getResultList();
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public Optional<AbstractLibrary> findById(String id) {
    	 EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             return Optional.fromNullable(em.find(AbstractLibrary.class, id));
         } finally {
             em.getTransaction().commit();
             em.close();
         }
    }

    @Override
    public void delete(AbstractLibrary library) {
    	EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            AbstractLibrary toDelete = em.find(AbstractLibrary.class, library.getId());
            if (toDelete != null) {
                em.remove(toDelete);
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}
