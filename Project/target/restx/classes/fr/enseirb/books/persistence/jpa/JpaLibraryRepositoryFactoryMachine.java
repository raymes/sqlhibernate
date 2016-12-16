package fr.enseirb.books.persistence.jpa;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.persistence.jpa.JpaLibraryRepository;
import fr.enseirb.books.persistence.LibraryRepository;

@Machine
public class JpaLibraryRepositoryFactoryMachine extends SingleNameFactoryMachine<FactoryMachine> {
    public static final Name<LibraryRepository> NAME = Name.of(LibraryRepository.class, "LibraryRepository");

    public JpaLibraryRepositoryFactoryMachine() {
        super(0, new StdMachineEngine<FactoryMachine>(
                    Name.of(FactoryMachine.class, "JpaLibraryRepositoryLibraryRepositoryAlternative"), BoundlessComponentBox.FACTORY) {
                private Factory.Query<String> query = Factory.Query.byName(Name.of(String.class, "persistence")).optional();

                @Override
                protected FactoryMachine doNewComponent(SatisfiedBOM satisfiedBOM) {
                    if (satisfiedBOM.getOne(query).isPresent()
                            && satisfiedBOM.getOne(query).get().getComponent().equals("jpa")) {
                        return new SingleNameFactoryMachine<LibraryRepository>(-1000,
                                        new StdMachineEngine<LibraryRepository>(NAME, -1000, BoundlessComponentBox.FACTORY) {
private final Factory.Query<javax.persistence.EntityManagerFactory> emf = Factory.Query.byClass(javax.persistence.EntityManagerFactory.class).mandatory();
private final Factory.Query<fr.enseirb.books.persistence.BookRepository> bookRepository = Factory.Query.byClass(fr.enseirb.books.persistence.BookRepository.class).mandatory();

                                            @Override
                                            public BillOfMaterials getBillOfMaterial() {
                                                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
emf,
bookRepository
                                                ));
                                            }

                                            @Override
                                            protected LibraryRepository doNewComponent(SatisfiedBOM satisfiedBOM) {
                                                return new JpaLibraryRepository(
satisfiedBOM.getOne(emf).get().getComponent(),
satisfiedBOM.getOne(bookRepository).get().getComponent()
                                                );
                                            }
                                        });
                    } else {
                        return NoopFactoryMachine.INSTANCE;
                    }
                }

                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return BillOfMaterials.of(query);
                }
            });
    }
}