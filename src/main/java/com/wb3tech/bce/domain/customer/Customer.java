package com.wb3tech.bce.domain.customer;

import com.wb3tech.kernel.Entity;
import com.wb3tech.kernel.Identity;
import com.wb3tech.kernel.valueobject.Person;

public class Customer extends Entity implements CustomerEntity   {

    private Person person;

    private Customer(String firstName, String lastName) {
        this.setPerson(firstName, lastName);
        this.assignNewIdentity();
    }

    private Customer(Identity identity, String firstName, String lastName) {
        this(firstName, lastName);
        this.setId(identity);
    }

    private Customer(CustomerRequest request) {
        this(Identity.New(request.getId()), request.getFirstName(), request.getLastName());
    }

    public static Customer Of(CustomerRequest request) {
        return new Customer(request);
    }

    public static Customer Of(String firstName, String lastName) {
        return new Customer(firstName, lastName);
    }

    public static Customer Of(Identity identity, String firstName, String lastName) {
        return new Customer(identity, firstName, lastName);
    }

    public String getFirstName() {
        return this.person.getGivenName();
    }

    public String getLastName() {
        return this.person.getSurname();
    }

    public boolean equals(CustomerEntity entity) {
        var id = this.getId().equals(entity.getId());
        var firstName = this.person.getGivenName().equals(entity.getFirstName());
        var lastName = this.person.getSurname().equals(entity.getLastName());
        return id && firstName && lastName;
    }

    private void setPerson(String firstName, String lastName) {
        this.person = Person.Of(firstName, lastName);
    }

}
