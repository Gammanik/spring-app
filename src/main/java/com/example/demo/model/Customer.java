package com.example.demo.model;

import org.springframework.data.annotation.Id;


public class Customer {

    @Id private String id;
    public String getId() {
        return id;
    }

    private final String firstName;
    private final String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            var c = ((Customer) obj);
            return firstName.equals(c.firstName) &&
                    lastName.equals(c.lastName);
        } else {
            return false;
        }
    }
}
