package com.example.demo;

import com.example.demo.model.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    @ResponseBody public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    @ResponseBody public Optional<Customer> findCustomerById(@RequestParam String id) {
        return repository.findById(id);
    }

    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    @ResponseBody public List<String> getCustomerIds() {
        return repository.findAll().stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/api/customers", method = RequestMethod.DELETE)
    @ResponseBody public void deleteCustomers() {
        repository.deleteAll();
    }

    @RequestMapping(value="/api/customers/all", method = RequestMethod.GET)
    @ResponseBody public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
