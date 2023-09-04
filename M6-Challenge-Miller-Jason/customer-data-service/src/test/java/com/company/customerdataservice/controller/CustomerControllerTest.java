package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();

    // Create a testing customer before testing
    Customer customer = new Customer();

    @BeforeEach
    public void setUp() {
        // Set the properties for the customer object
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Netflix Street");
        customer.setAddress2("456 Netflix Street");
        customer.setCity("New York");
        customer.setState("NY");
        customer.setPostalCode("10001");
        customer.setCountry("USA");
        customerRepository.save(customer);
    }

    // Testing posting a new customer
    @Test
    public void shouldCreateCustomer() throws Exception {
        Customer customer = new Customer();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Testing getting all customers
    @Test
    public void shouldGetAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer"))
                .andExpect(status().isOk());
    }

    // Testing getting a customer by Id
    @Test
    public void shouldGetCustomerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/{id}", customer.getId()))
                .andExpect(status().isOk());
    }

    // Testing updating a customer
    @Test
    public void shouldUpdateCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/{id}", 1)
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Testing deleting a customer
    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/{id}", 1))
                .andExpect(status().isNoContent());
    }

    // Testing getting all customers by state
    @Test
    public void shouldGetCustomersByState() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/state/{state}", "NY"))
                .andExpect(status().isOk());
    }
}
