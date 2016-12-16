package fr.enseirb.books.persistence.jpa;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.persistence.jpa.JpaModule;


@Machine
public class JpaModuleFactoryMachine extends DefaultFactoryMachine {
    private static final JpaModule module = new JpaModule();

    public JpaModuleFactoryMachine() {
        super(0, new MachineEngine[] {
            new StdMachineEngine<javax.persistence.EntityManagerFactory>(Name.of(javax.persistence.EntityManagerFactory.class, "entityManagerFactory"), 0, BoundlessComponentBox.FACTORY) {
        private final Factory.Query<javax.sql.DataSource> ds = Factory.Query.byClass(javax.sql.DataSource.class).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        ds
                    ));
                }

                @Override
                public javax.persistence.EntityManagerFactory doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.entityManagerFactory(
	        satisfiedBOM.getOne(ds).get().getComponent()
	                    );
	                
                }
            },

        });
    }
}
