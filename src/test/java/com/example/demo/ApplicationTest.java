package com.example.demo;

import com.example.demo.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    @Autowired private MockMvc mockMvc;

    @BeforeEach
    void clean() throws Exception {
        mockMvc.perform(delete("/api/customers"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        var customer = new Customer("name", "sname");
        createCustomer(customer);
    }

    @Test
    public void shouldGetCustomerIds() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(0)));


        for (int i = 0; i < 10; i++) {
            createCustomer(new Customer("name" + i, "surname" + i));
        }

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(10)));
    }


    private void createCustomer(Customer c) throws Exception {
        var objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/customer")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c))
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(c.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(c.getLastName())));
    }
}
