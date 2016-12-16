package fr.enseirb.books.web.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.web.rest.LibraryResource;

@Machine
public class LibraryResourceFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.web.rest.LibraryResource> {
    public static final Name<fr.enseirb.books.web.rest.LibraryResource> NAME = Name.of(fr.enseirb.books.web.rest.LibraryResource.class, "LibraryResource");

    public LibraryResourceFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.web.rest.LibraryResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.service.LibraryService> libraryService = Factory.Query.byClass(fr.enseirb.books.service.LibraryService.class).mandatory();
private final Factory.Query<fr.enseirb.books.service.BookService> bookService = Factory.Query.byClass(fr.enseirb.books.service.BookService.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
libraryService,
bookService
                ));
            }

            @Override
            protected fr.enseirb.books.web.rest.LibraryResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new LibraryResource(
satisfiedBOM.getOne(libraryService).get().getComponent(),
satisfiedBOM.getOne(bookService).get().getComponent()
                );
            }
        });
    }

}
