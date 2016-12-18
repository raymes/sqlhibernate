package fr.enseirb.books.domain;

import javax.persistence.Entity;

@Entity
public class ELibrary extends AbstractLibrary {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
