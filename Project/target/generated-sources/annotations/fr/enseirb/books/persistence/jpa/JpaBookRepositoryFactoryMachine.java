package fr.enseirb.books.persistence.jpa;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.persistence.jpa.JpaBookRepository;
import fr.enseirb.books.persistence.BookRepository;

@Machine
public class JpaBookRepositoryFactoryMachine extends SingleNameFactoryMachine<FactoryMachine> {
    public static final Name<BookRepository> NAME = Name.of(BookRepository.class, "BookRepository");

    public JpaBookRepositoryFactoryMachine() {
        super(0, new StdMachineEngine<FactoryMachine>(
                    Name.of(FactoryMachine.class, "JpaBookRepositoryBookRepositoryAlternative"), BoundlessComponentBox.FACTORY) {
                private Factory.Query<String> query = Factory.Query.byName(Name.of(String.class, "persistence")).optional();

                @Override
                protected FactoryMachine doNewComponent(SatisfiedBOM satisfiedBOM) {
                    if (satisfiedBOM.getOne(query).isPresent()
                            && satisfiedBOM.getOne(query).get().getComponent().equals("jpa")) {
                        return new SingleNameFactoryMachine<BookRepository>(-1000,
                                        new StdMachineEngine<BookRepository>(NAME, -1000, BoundlessComponentBox.FACTORY) {
private final Factory.Query<javax.persistence.EntityManagerFactory> emf = Factory.Query.byClass(javax.persistence.EntityManagerFactory.class).mandatory();
private final Factory.Query<fr.enseirb.books.persistence.AuthorRepository> authorRepository = Factory.Query.byClass(fr.enseirb.books.persistence.AuthorRepository.class).mandatory();

                                            @Override
                                            public BillOfMaterials getBillOfMaterial() {
                                                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
emf,
authorRepository
                                                ));
                                            }

                                            @Override
                                            protected BookRepository doNewComponent(SatisfiedBOM satisfiedBOM) {
                                                return new JpaBookRepository(
satisfiedBOM.getOne(emf).get().getComponent(),
satisfiedBOM.getOne(authorRepository).get().getComponent()
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