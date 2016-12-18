package fr.enseirb.books.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String body;
    private String zipcode;
    private String city;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
