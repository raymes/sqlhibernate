package fr.enseirb.books.persistence;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.Book;

public interface BookRepository {

    Book create(Book book);

    Book update(Book book);

    Iterable<Book> find();

    Optional<Book> findById(String id);

    Iterable<String> findTitles();

    Iterable<Book> findByAuthorLastName(String lastName);

    void delete(Book book);
}
