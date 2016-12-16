package fr.enseirb.books.persistence.jpa;

import fr.enseirb.books.domain.AbstractLibrary;
import fr.enseirb.books.domain.Address;
import fr.enseirb.books.domain.Author;
import fr.enseirb.books.domain.Book;
import fr.enseirb.books.domain.ELibrary;
import fr.enseirb.books.domain.Library;
import org.hibernate.ejb.Ejb3Configuration;
import restx.factory.Module;
import restx.factory.Provides;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Module
public class JpaModule {

    @Provides
    @SuppressWarnings("deprecation")
    public EntityManagerFactory entityManagerFactory(DataSource ds) {
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.dialect" ,"org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql" ,"true");

        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.addProperties(properties);
        cfg.setDataSource(ds);
        cfg.addPackage("fr.enseirb.books");
        cfg.addAnnotatedClass(AbstractLibrary.class);
        cfg.addAnnotatedClass(Library.class);
        cfg.addAnnotatedClass(ELibrary.class);
        cfg.addAnnotatedClass(Book.class);
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(Address.class);
        return cfg.buildEntityManagerFactory();
    }
}
