package fr.enseirb.books.web.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import fr.enseirb.books.web.rest.BookResourceRouter;

@Machine
public class BookResourceRouterFactoryMachine extends SingleNameFactoryMachine<fr.enseirb.books.web.rest.BookResourceRouter> {
    public static final Name<fr.enseirb.books.web.rest.BookResourceRouter> NAME = Name.of(fr.enseirb.books.web.rest.BookResourceRouter.class, "BookResourceRouter");

    public BookResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<fr.enseirb.books.web.rest.BookResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<fr.enseirb.books.web.rest.BookResource> resource = Factory.Query.byClass(fr.enseirb.books.web.rest.BookResource.class).mandatory();
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
            protected fr.enseirb.books.web.rest.BookResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new BookResourceRouter(
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
