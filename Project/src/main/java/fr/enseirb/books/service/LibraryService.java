package fr.enseirb.books.service;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.persistence.LibraryRepository;
import restx.factory.Component;

@Component
public class LibraryService {

    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public <L extends AbstractLibrary> L create(L library) {
        return libraryRepository.create(library);
    }

    public <L extends AbstractLibrary> L update(L library) {
        return libraryRepository.update(library);
    }

    public Iterable<AbstractLibrary> find() {
        return libraryRepository.find();
    }

    public Optional<AbstractLibrary> findById(String id) {
        return libraryRepository.findById(id);
    }

    public void delete(String id) {
        Optional<AbstractLibrary> libraryOptional = findById(id);

        if (libraryOptional.isPresent()) {
            libraryRepository.delete(libraryOptional.get());
        }
    }

    public AbstractLibrary addBook(AbstractLibrary library, Book book) {
        library.getBooks().add(book);
        return update(library);
    }
}
