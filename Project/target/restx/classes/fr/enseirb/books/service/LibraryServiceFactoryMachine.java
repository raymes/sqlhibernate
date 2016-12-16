package fr.enseirb.books.service;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.service.LibraryService;

@Machine
public class LibraryServiceFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.service.LibraryService> {
    public static final Name<fr.enseirb.books.service.LibraryService> NAME = Name.of(fr.enseirb.books.service.LibraryService.class, "LibraryService");

    public LibraryServiceFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.service.LibraryService>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.persistence.LibraryRepository> libraryRepository = Factory.Query.byClass(fr.enseirb.books.persistence.LibraryRepository.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
libraryRepository
                ));
            }

            @Override
            protected fr.enseirb.books.service.LibraryService doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new LibraryService(
satisfiedBOM.getOne(libraryRepository).get().getComponent()
                );
            }
        });
    }

}
