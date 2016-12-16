package fr.enseirb.books.web.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.web.rest.BookResource;

@Machine
public class BookResourceFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.web.rest.BookResource> {
    public static final Name<fr.enseirb.books.web.rest.BookResource> NAME = Name.of(fr.enseirb.books.web.rest.BookResource.class, "BookResource");

    public BookResourceFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.web.rest.BookResource>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.service.BookService> bookService = Factory.Query.byClass(fr.enseirb.books.service.BookService.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
bookService
                ));
            }

            @Override
            protected fr.enseirb.books.web.rest.BookResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new BookResource(
satisfiedBOM.getOne(bookService).get().getComponent()
                );
            }
        });
    }

}
