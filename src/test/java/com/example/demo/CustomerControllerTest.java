package com.example.demo;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private CustomerController controller;

    @BeforeEach
    private void clean() {
        controller.deleteCustomers();
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldCreateCustomer() {
        var customer = new Customer("testName", "testLastName");
        controller.createCustomer(customer);

        assertThat(controller.getCustomerIds().size()).isEqualTo(1);
        var id = controller.getCustomerIds().get(0);
        var result = controller.findCustomerById(id);
        assertThat(result.isPresent());
        assert result.isPresent();
        var resCustomer = result.get();
        assertThat(resCustomer.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(resCustomer.getLastName()).isEqualTo(customer.getLastName());
    }


    @Test
    void shouldGetCustomerIds() {
        var customers = new ArrayList<Customer>();
        for (int i = 0; i < 10; i++) {
            var customer = new Customer("name" + i, "surname" + i);
            customers.add(customer);
            assertThat(controller.createCustomer(customer)).isEqualTo(customer);
        }

        var resCustomers = controller.getCustomerIds();
        assertThat(resCustomers.size()).isEqualTo(customers.size());

        var ids = customers.stream().map(Customer::getId).collect(Collectors.toList());
        resCustomers.forEach(customerId ->
                assertThat(ids.contains(customerId)));
    }

}
