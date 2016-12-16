package fr.enseirb.books.web.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.web.rest.LibraryResourceRouter;

@Machine
public class LibraryResourceRouterFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.web.rest.LibraryResourceRouter> {
    public static final Name<fr.enseirb.books.web.rest.LibraryResourceRouter> NAME = Name.of(fr.enseirb.books.web.rest.LibraryResourceRouter.class, "LibraryResourceRouter");

    public LibraryResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.web.rest.LibraryResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.web.rest.LibraryResource> resource = Factory.Query.byClass(fr.enseirb.books.web.rest.LibraryResource.class).mandatory();
private final Factory.Query<restx.entity.EntityRequestBodyReaderRegistry> readerRegistry = Factory.Query.byClass(restx.entity.EntityRequestBodyReaderRegistry.class).mandatory();
private final Factory.Query<restx.entity.EntityResponseWriterRegistry> writerRegistry = Factory.Query.byClass(restx.entity.EntityResponseWriterRegistry.class).mandatory();
private final Factory.Query<restx.converters.MainStringConverter> converter = Factory.Query.byClass(restx.converters.MainStringConverter.class).mandatory();
private final Factory.Query<javax.validation.Validator> validator = Factory.Query.byClass(javax.validation.Validator.class).optional();
private final Factory.Query<restx.security.RestxSecurityManager> securityManager = Factory.Query.byClass(restx.security.RestxSecurityManager.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
resource,
readerRegistry,
writerRegistry,
converter,
validator,
securityManager
                ));
            }

            @Override
            protected fr.enseirb.books.web.rest.LibraryResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new LibraryResourceRouter(
satisfiedBOM.getOne(resource).get().getComponent(),
satisfiedBOM.getOne(readerRegistry).get().getComponent(),
satisfiedBOM.getOne(writerRegistry).get().getComponent(),
satisfiedBOM.getOne(converter).get().getComponent(),
satisfiedBOM.getOneAsComponent(validator),
satisfiedBOM.getOne(securityManager).get().getComponent()
                );
            }
        });
    }

}
