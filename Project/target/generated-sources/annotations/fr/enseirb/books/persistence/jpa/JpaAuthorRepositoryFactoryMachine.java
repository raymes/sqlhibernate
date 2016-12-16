package fr.enseirb.books.persistence.jpa;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.persistence.jpa.JpaAuthorRepository;
import fr.enseirb.books.persistence.AuthorRepository;

@Machine
public class JpaAuthorRepositoryFactoryMachine extends SingleNameFactoryMachine<FactoryMachine> {
    public static final Name<AuthorRepository> NAME = Name.of(AuthorRepository.class, "AuthorRepository");

    public JpaAuthorRepositoryFactoryMachine() {
        super(0, new StdMachineEngine<FactoryMachine>(
                    Name.of(FactoryMachine.class, "JpaAuthorRepositoryAuthorRepositoryAlternative"), BoundlessComponentBox.FACTORY) {
                private Factory.Query<String> query = Factory.Query.byName(Name.of(String.class, "persistence")).optional();

                @Override
                protected FactoryMachine doNewComponent(SatisfiedBOM satisfiedBOM) {
                    if (satisfiedBOM.getOne(query).isPresent()
                            && satisfiedBOM.getOne(query).get().getComponent().equals("jpa")) {
                        return new SingleNameFactoryMachine<AuthorRepository>(-1000,
                                        new StdMachineEngine<AuthorRepository>(NAME, -1000, BoundlessComponentBox.FACTORY) {
private final Factory.Query<javax.persistence.EntityManagerFactory> emf = Factory.Query.byClass(javax.persistence.EntityManagerFactory.class).mandatory();

                                            @Override
                                            public BillOfMaterials getBillOfMaterial() {
                                                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
emf
                                                ));
                                            }

                                            @Override
                                            protected AuthorRepository doNewComponent(SatisfiedBOM satisfiedBOM) {
                                                return new JpaAuthorRepository(
satisfiedBOM.getOne(emf).get().getComponent()
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