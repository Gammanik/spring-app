package com.example.demo;

import java.util.List;

import com.example.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
