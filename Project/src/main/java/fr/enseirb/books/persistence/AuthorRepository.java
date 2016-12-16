package fr.enseirb.books.persistence;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.Author;

public interface AuthorRepository {

    Author create(Author author);

    Optional<Author> findById(String id);
}
