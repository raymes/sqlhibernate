package fr.enseirb.books.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

import restx.common.Types;
import restx.*;
import restx.entity.*;
import restx.http.*;
import restx.factory.*;
import restx.security.*;
import static restx.security.Permissions.*;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

@Component(priority = 0)

public class BookResourceRouter extends RestxRouter {

    public BookResourceRouter(
                    final BookResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "BookResourceRouter", new RestxRoute[] {
        new StdEntityRoute<fr.enseirb.books.domain.Book, fr.enseirb.books.domain.Book>("default#BookResource#create",
                readerRegistry.<fr.enseirb.books.domain.Book>build(fr.enseirb.books.domain.Book.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.Book>build(fr.enseirb.books.domain.Book.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/books"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.Book> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.Book body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.create(
                        /* [BODY] book */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription book = new OperationParameterDescription();
                book.name = "book";
                book.paramType = OperationParameterDescription.ParamType.body;
                book.dataType = "Book";
                book.schemaKey = "fr.enseirb.books.domain.Book";
                book.required = true;
                operation.parameters.add(book);


                operation.responseClass = "Book";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#create(fr.enseirb.books.domain.Book)";
            }
        },
        new StdEntityRoute<fr.enseirb.books.domain.Book, fr.enseirb.books.domain.Book>("default#BookResource#update",
                readerRegistry.<fr.enseirb.books.domain.Book>build(fr.enseirb.books.domain.Book.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.Book>build(fr.enseirb.books.domain.Book.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/books/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.Book> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.Book body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.update(
                        /* [PATH] id */ match.getPathParam("id"),
                        /* [BODY] book */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription id = new OperationParameterDescription();
                id.name = "id";
                id.paramType = OperationParameterDescription.ParamType.path;
                id.dataType = "string";
                id.schemaKey = "";
                id.required = true;
                operation.parameters.add(id);

                OperationParameterDescription book = new OperationParameterDescription();
                book.name = "book";
                book.paramType = OperationParameterDescription.ParamType.body;
                book.dataType = "Book";
                book.schemaKey = "fr.enseirb.books.domain.Book";
                book.required = true;
                operation.parameters.add(book);


                operation.responseClass = "Book";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#update(java.lang.String,fr.enseirb.books.domain.Book)";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<fr.enseirb.books.domain.Book>>("default#BookResource#find",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<fr.enseirb.books.domain.Book>>build(Types.newParameterizedType(java.lang.Iterable.class, fr.enseirb.books.domain.Book.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/books"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<fr.enseirb.books.domain.Book>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.find(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[Book]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#find()";
            }
        },
        new StdEntityRoute<Void, fr.enseirb.books.domain.Book>("default#BookResource#findById",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.Book>build(fr.enseirb.books.domain.Book.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/books/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.Book> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return resource.findById(
                        /* [PATH] id */ match.getPathParam("id")
                );
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription id = new OperationParameterDescription();
                id.name = "id";
                id.paramType = OperationParameterDescription.ParamType.path;
                id.dataType = "string";
                id.schemaKey = "";
                id.required = true;
                operation.parameters.add(id);


                operation.responseClass = "Book";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#findById(java.lang.String)";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<java.lang.String>>("default#BookResource#findTitles",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<java.lang.String>>build(Types.newParameterizedType(java.lang.Iterable.class, java.lang.String.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/books/names"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<java.lang.String>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.findTitles(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[string]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#findTitles()";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<fr.enseirb.books.domain.Book>>("default#BookResource#findByAuthorLastName",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<fr.enseirb.books.domain.Book>>build(Types.newParameterizedType(java.lang.Iterable.class, fr.enseirb.books.domain.Book.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/books/author/{lastName}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<fr.enseirb.books.domain.Book>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.findByAuthorLastName(
                        /* [PATH] lastName */ match.getPathParam("lastName")
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription lastName = new OperationParameterDescription();
                lastName.name = "lastName";
                lastName.paramType = OperationParameterDescription.ParamType.path;
                lastName.dataType = "string";
                lastName.schemaKey = "";
                lastName.required = true;
                operation.parameters.add(lastName);


                operation.responseClass = "LIST[Book]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Book";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#findByAuthorLastName(java.lang.String)";
            }
        },
        new StdEntityRoute<Void, Empty>("default#BookResource#delete",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<Empty>build(void.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("DELETE", "/books/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<Empty> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                resource.delete(
                        /* [PATH] id */ match.getPathParam("id")
                );
                return Optional.of(Empty.EMPTY);
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription id = new OperationParameterDescription();
                id.name = "id";
                id.paramType = OperationParameterDescription.ParamType.path;
                id.dataType = "string";
                id.schemaKey = "";
                id.required = true;
                operation.parameters.add(id);


                operation.responseClass = "void";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "";
                operation.sourceLocation = "fr.enseirb.books.web.rest.BookResource#delete(java.lang.String)";
            }
        },
        });
    }

}
