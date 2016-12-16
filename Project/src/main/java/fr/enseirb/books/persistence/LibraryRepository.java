package fr.enseirb.books.persistence;

import com.google.common.base.Optional;
import fr.enseirb.books.domain.AbstractLibrary;

public interface LibraryRepository {

    <L extends AbstractLibrary> L create(L library);

    <L extends AbstractLibrary> L update(L library);

    Iterable<AbstractLibrary> find();

    Optional<AbstractLibrary> findById(String id);

    void delete(AbstractLibrary library);
}
