package fr.enseirb.books.service;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.persistence.BookRepository;
import restx.factory.Component;

@Component
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        return bookRepository.create(book);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public Iterable<Book> find() {
        return bookRepository.find();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Iterable<String> findTitles() {
        return bookRepository.findTitles();
    }

    public void delete(String id) {
        Optional<Book> bookOptional = findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
        }
    }

    public Iterable<Book> findByAuthorLastName(String lastName) {
        return bookRepository.findByAuthorLastName(lastName);
    }
}
