package fr.enseirb.books.service;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.service.BookService;

@Machine
public class BookServiceFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.service.BookService> {
    public static final Name<fr.enseirb.books.service.BookService> NAME = Name.of(fr.enseirb.books.service.BookService.class, "BookService");

    public BookServiceFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.service.BookService>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.persistence.BookRepository> bookRepository = Factory.Query.byClass(fr.enseirb.books.persistence.BookRepository.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
bookRepository
                ));
            }

            @Override
            protected fr.enseirb.books.service.BookService doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new BookService(
satisfiedBOM.getOne(bookRepository).get().getComponent()
                );
            }
        });
    }

}
