package fr.enseirb.books.persistence;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.persistence.H2JdbcModule;


@Machine
public class H2JdbcModuleFactoryMachine extends DefaultFactoryMachine {
    private static final H2JdbcModule module = new H2JdbcModule();

    public H2JdbcModuleFactoryMachine() {
        super(0, new MachineEngine[] {
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "restx.jdbc.url"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.jdbcUrl(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "restx.jdbc.username"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.jdbcUsername(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<java.lang.String>(Name.of(java.lang.String.class, "restx.jdbc.password"), 0, BoundlessComponentBox.FACTORY) {
        
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        
                    ));
                }

                @Override
                public java.lang.String doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.jdbcPassword(
	        
	                    );
	                
                }
            },
            new StdMachineEngine<javax.sql.DataSource>(Name.of(javax.sql.DataSource.class, "datasource"), 0, BoundlessComponentBox.FACTORY) {
        private final Factory.Query<java.lang.String> jdbcUrl = Factory.Query.byName(Name.of(java.lang.String.class, "restx.jdbc.url")).mandatory();
private final Factory.Query<java.lang.String> jdbcUsername = Factory.Query.byName(Name.of(java.lang.String.class, "restx.jdbc.username")).mandatory();
private final Factory.Query<java.lang.String> jdbcPassword = Factory.Query.byName(Name.of(java.lang.String.class, "restx.jdbc.password")).mandatory();
                @Override
                public BillOfMaterials getBillOfMaterial() {
                    return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
        jdbcUrl,
jdbcUsername,
jdbcPassword
                    ));
                }

                @Override
                public javax.sql.DataSource doNewComponent(SatisfiedBOM satisfiedBOM) {
	                
	                    return module.datasource(
	        satisfiedBOM.getOne(jdbcUrl).get().getComponent(),
satisfiedBOM.getOne(jdbcUsername).get().getComponent(),
satisfiedBOM.getOne(jdbcPassword).get().getComponent()
	                    );
	                
                }
            },

        });
    }
}
