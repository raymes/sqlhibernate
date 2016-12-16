package fr.enseirb.books.domain;

public class Library extends AbstractLibrary {

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
