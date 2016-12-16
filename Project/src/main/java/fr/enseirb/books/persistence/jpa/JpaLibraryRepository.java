package fr.enseirb.books.persistence.jpa;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.persistence.BookRepository;
import fr.enseirb.books.persistence.LibraryRepository;
import restx.factory.Alternative;
import restx.factory.When;

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
        return null;
    }

    @Override
    public <L extends AbstractLibrary> L update(L library) {
        return null;
    }

    @Override
    public Iterable<AbstractLibrary> find() {
        return null;
    }

    @Override
    public Optional<AbstractLibrary> findById(String id) {
        return null;
    }

    @Override
    public void delete(AbstractLibrary library) {
    }
}
