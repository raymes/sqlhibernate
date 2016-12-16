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

public class LibraryResourceRouter extends RestxRouter {

    public LibraryResourceRouter(
                    final LibraryResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager) {
        super(
            "default", "LibraryResourceRouter", new RestxRoute[] {
        new StdEntityRoute<fr.enseirb.books.domain.Library, fr.enseirb.books.domain.Library>("default#LibraryResource#createLibrary",
                readerRegistry.<fr.enseirb.books.domain.Library>build(fr.enseirb.books.domain.Library.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.Library>build(fr.enseirb.books.domain.Library.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/libraries/library"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.Library> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.Library body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.createLibrary(
                        /* [BODY] library */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription library = new OperationParameterDescription();
                library.name = "library";
                library.paramType = OperationParameterDescription.ParamType.body;
                library.dataType = "Library";
                library.schemaKey = "fr.enseirb.books.domain.Library";
                library.required = true;
                operation.parameters.add(library);


                operation.responseClass = "Library";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.Library";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.Library";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#createLibrary(fr.enseirb.books.domain.Library)";
            }
        },
        new StdEntityRoute<fr.enseirb.books.domain.ELibrary, fr.enseirb.books.domain.ELibrary>("default#LibraryResource#createELibrary",
                readerRegistry.<fr.enseirb.books.domain.ELibrary>build(fr.enseirb.books.domain.ELibrary.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.ELibrary>build(fr.enseirb.books.domain.ELibrary.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("POST", "/libraries/eLibrary"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.ELibrary> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.ELibrary body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.createELibrary(
                        /* [BODY] library */ checkValid(validator, body)
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                                OperationParameterDescription library = new OperationParameterDescription();
                library.name = "library";
                library.paramType = OperationParameterDescription.ParamType.body;
                library.dataType = "ELibrary";
                library.schemaKey = "fr.enseirb.books.domain.ELibrary";
                library.required = true;
                operation.parameters.add(library);


                operation.responseClass = "ELibrary";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.ELibrary";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.ELibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#createELibrary(fr.enseirb.books.domain.ELibrary)";
            }
        },
        new StdEntityRoute<fr.enseirb.books.domain.Library, fr.enseirb.books.domain.AbstractLibrary>("default#LibraryResource#updateLibrary",
                readerRegistry.<fr.enseirb.books.domain.Library>build(fr.enseirb.books.domain.Library.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.AbstractLibrary>build(fr.enseirb.books.domain.AbstractLibrary.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/libraries/library/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.AbstractLibrary> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.Library body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.updateLibrary(
                        /* [PATH] id */ match.getPathParam("id"),
                        /* [BODY] library */ checkValid(validator, body)
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

                OperationParameterDescription library = new OperationParameterDescription();
                library.name = "library";
                library.paramType = OperationParameterDescription.ParamType.body;
                library.dataType = "Library";
                library.schemaKey = "fr.enseirb.books.domain.Library";
                library.required = true;
                operation.parameters.add(library);


                operation.responseClass = "AbstractLibrary";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.Library";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.AbstractLibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#updateLibrary(java.lang.String,fr.enseirb.books.domain.Library)";
            }
        },
        new StdEntityRoute<fr.enseirb.books.domain.ELibrary, fr.enseirb.books.domain.AbstractLibrary>("default#LibraryResource#updateELibrary",
                readerRegistry.<fr.enseirb.books.domain.ELibrary>build(fr.enseirb.books.domain.ELibrary.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.AbstractLibrary>build(fr.enseirb.books.domain.AbstractLibrary.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/libraries/eLibrary/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.AbstractLibrary> doRoute(RestxRequest request, RestxRequestMatch match, fr.enseirb.books.domain.ELibrary body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.updateELibrary(
                        /* [PATH] id */ match.getPathParam("id"),
                        /* [BODY] library */ checkValid(validator, body)
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

                OperationParameterDescription library = new OperationParameterDescription();
                library.name = "library";
                library.paramType = OperationParameterDescription.ParamType.body;
                library.dataType = "ELibrary";
                library.schemaKey = "fr.enseirb.books.domain.ELibrary";
                library.required = true;
                operation.parameters.add(library);


                operation.responseClass = "AbstractLibrary";
                operation.inEntitySchemaKey = "fr.enseirb.books.domain.ELibrary";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.AbstractLibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#updateELibrary(java.lang.String,fr.enseirb.books.domain.ELibrary)";
            }
        },
        new StdEntityRoute<Void, fr.enseirb.books.domain.AbstractLibrary>("default#LibraryResource#addBook",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.AbstractLibrary>build(fr.enseirb.books.domain.AbstractLibrary.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("PUT", "/libraries/{id}/book/{bookId}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.AbstractLibrary> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.addBook(
                        /* [PATH] id */ match.getPathParam("id"),
                        /* [PATH] bookId */ match.getPathParam("bookId")
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

                OperationParameterDescription bookId = new OperationParameterDescription();
                bookId.name = "bookId";
                bookId.paramType = OperationParameterDescription.ParamType.path;
                bookId.dataType = "string";
                bookId.schemaKey = "";
                bookId.required = true;
                operation.parameters.add(bookId);


                operation.responseClass = "AbstractLibrary";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.AbstractLibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#addBook(java.lang.String,java.lang.String)";
            }
        },
        new StdEntityRoute<Void, java.lang.Iterable<fr.enseirb.books.domain.AbstractLibrary>>("default#LibraryResource#find",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.Iterable<fr.enseirb.books.domain.AbstractLibrary>>build(Types.newParameterizedType(java.lang.Iterable.class, fr.enseirb.books.domain.AbstractLibrary.class), Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/libraries"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<java.lang.Iterable<fr.enseirb.books.domain.AbstractLibrary>> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, isAuthenticated());
                return Optional.of(resource.find(
                        
                ));
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                

                operation.responseClass = "LIST[AbstractLibrary]";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.AbstractLibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#find()";
            }
        },
        new StdEntityRoute<Void, fr.enseirb.books.domain.AbstractLibrary>("default#LibraryResource#findById",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<fr.enseirb.books.domain.AbstractLibrary>build(fr.enseirb.books.domain.AbstractLibrary.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("GET", "/libraries/{id}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT) {
            @Override
            protected Optional<fr.enseirb.books.domain.AbstractLibrary> doRoute(RestxRequest request, RestxRequestMatch match, Void body) throws IOException {
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


                operation.responseClass = "AbstractLibrary";
                operation.inEntitySchemaKey = "";
                operation.outEntitySchemaKey = "fr.enseirb.books.domain.AbstractLibrary";
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#findById(java.lang.String)";
            }
        },
        new StdEntityRoute<Void, Empty>("default#LibraryResource#delete",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<Empty>build(void.class, Optional.<String>absent()),
                new StdRestxRequestMatcher("DELETE", "/libraries/{id}"),
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
                operation.sourceLocation = "fr.enseirb.books.web.rest.LibraryResource#delete(java.lang.String)";
            }
        },
        });
    }

}
