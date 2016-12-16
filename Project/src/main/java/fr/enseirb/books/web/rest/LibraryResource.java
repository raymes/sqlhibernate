package fr.enseirb.books.web.rest;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.domain.ELibrary;
import fr.enseirb.books.domain.Library;
import fr.enseirb.books.service.BookService;
import fr.enseirb.books.service.LibraryService;
import restx.WebException;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.common.MorePreconditions;
import restx.factory.Component;
import restx.http.HttpStatus;

@RestxResource("/libraries")
@Component
public class LibraryResource {

    private LibraryService libraryService;
    private BookService bookService;

    public LibraryResource(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @POST("/library")
    public Library createLibrary(Library library) {
        return libraryService.create(library);
    }

    @POST("/eLibrary")
    public ELibrary createELibrary(ELibrary library) {
        return libraryService.create(library);
    }

    @PUT("/library/{id}")
    public AbstractLibrary updateLibrary(String id, Library library) {
        MorePreconditions.checkEquals("id", id, "library.id", library.getId());
        return libraryService.update(library);
    }

    @PUT("/eLibrary/{id}")
    public AbstractLibrary updateELibrary(String id, ELibrary library) {
        MorePreconditions.checkEquals("id", id, "library.id", library.getId());
        return libraryService.update(library);
    }

    @PUT("/{id}/book/{bookId}")
    public AbstractLibrary addBook(String id, String bookId) {
        Optional<AbstractLibrary> libraryOptional = findById(id);
        Optional<Book> bookOptional = bookService.findById(bookId);

        if (libraryOptional.isPresent() && bookOptional.isPresent()) {
            return libraryService.addBook(libraryOptional.get(), bookOptional.get());
        } else {
            throw new WebException(HttpStatus.NOT_FOUND,
                    (!libraryOptional.isPresent() ? "Library not found: " + id + ". " : "") +
                            (!bookOptional.isPresent() ? "Book not found: " + bookId + "." : ""));
        }
    }

    @GET("")
    public Iterable<AbstractLibrary> find() {
        return libraryService.find();
    }

    @GET("/{id}")
    public Optional<AbstractLibrary> findById(String id) {
        return libraryService.findById(id);
    }

    @DELETE("/{id}")
    public void delete(String id) {
        libraryService.delete(id);
    }
}
