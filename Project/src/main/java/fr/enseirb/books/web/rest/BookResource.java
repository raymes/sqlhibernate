package fr.enseirb.books.web.rest;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.service.BookService;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.common.MorePreconditions;
import restx.factory.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestxResource("/books")
@Component
public class BookResource {

    public static final String DEFAULT_ENCODING = "UTF8";
    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @POST("")
    public Book create(Book book) {
        return bookService.create(book);
    }

    @PUT("/{id}")
    public Book update(String id, Book book) {
        MorePreconditions.checkEquals("id", id, "book.id", book.getId());
        return bookService.update(book);
    }

    @GET("")
    public Iterable<Book> find() {
        return bookService.find();
    }
    
    @GET("/names")
    public Iterable<String> findTitles() {
        return bookService.findTitles();
    }
    
    @GET("/{id}")
    public Optional<Book> findById(String id) {
        return bookService.findById(id);
    }

    

    @GET("/author/{lastName}")
    public Iterable<Book> findByAuthorLastName(String lastName) throws UnsupportedEncodingException {
        return bookService.findByAuthorLastName(URLDecoder.decode(lastName, DEFAULT_ENCODING));
    }

    @DELETE("/{id}")
    public void delete(String id) {
        bookService.delete(id);
    }
}
