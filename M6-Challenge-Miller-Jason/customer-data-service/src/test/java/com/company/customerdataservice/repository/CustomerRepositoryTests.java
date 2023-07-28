package com.company.customerdataservice.repository;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void addCustomer() {
        // Arrange
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("john.doe@example.com");
        newCustomer.setCompany("ABC Corp");
        newCustomer.setPhone("123-456-7890");
        newCustomer.setAddress1("123 Main St");
        newCustomer.setAddress2("Apt 4B");
        newCustomer.setCity("New York");
        newCustomer.setState("NY");
        newCustomer.setPostalCode("10001");
        newCustomer.setCountry("USA");

        // Act
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Assert
        Optional<Customer> retrievedCustomer = customerRepository.findById(savedCustomer.getId());
        assertTrue(retrievedCustomer.isPresent()); // Verify if the customer was successfully retrieved
        retrievedCustomer.ifPresent(customer -> {
            assertEquals("John", customer.getFirstName());
            assertEquals("Doe", customer.getLastName());
            assertEquals("john.doe@example.com", customer.getEmail());
            assertEquals("ABC Corp", customer.getCompany());
            assertEquals("123-456-7890", customer.getPhone());
            assertEquals("123 Main St", customer.getAddress1());
            assertEquals("Apt 4B", customer.getAddress2());
            assertEquals("New York", customer.getCity());
            assertEquals("NY", customer.getState());
            assertEquals("10001", customer.getPostalCode());
            assertEquals("USA", customer.getCountry());
        });
    }

    @Test
    public void updateCustomer() {
        // Arrange
        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setCompany("ABC Corp");
        existingCustomer.setPhone("123-456-7890");
        existingCustomer.setAddress1("123 Main St");
        existingCustomer.setAddress2("Apt 4B");
        existingCustomer.setCity("New York");
        existingCustomer.setState("NY");
        existingCustomer.setPostalCode("10001");
        existingCustomer.setCountry("USA");
        customerRepository.save(existingCustomer);

        // Act
        existingCustomer.setFirstName("Jane");
        existingCustomer.setLastName("Smith");
        existingCustomer.setCompany("XYZ Ltd");
        customerRepository.save(existingCustomer);

        // Assert
        Optional<Customer> updatedCustomer = customerRepository.findById(existingCustomer.getId());
        assertTrue(updatedCustomer.isPresent());
        assertEquals("Jane", updatedCustomer.get().getFirstName());
        assertEquals("Smith", updatedCustomer.get().getLastName());
        assertEquals("john.doe@example.com", updatedCustomer.get().getEmail()); // Email remains unchanged
        assertEquals("XYZ Ltd", updatedCustomer.get().getCompany());
        assertEquals("123-456-7890", updatedCustomer.get().getPhone()); // Phone remains unchanged
        assertEquals("123 Main St", updatedCustomer.get().getAddress1()); // Address remains unchanged
        assertEquals("Apt 4B", updatedCustomer.get().getAddress2()); // Address remains unchanged
        assertEquals("New York", updatedCustomer.get().getCity()); // City remains unchanged
        assertEquals("NY", updatedCustomer.get().getState()); // State remains unchanged
        assertEquals("10001", updatedCustomer.get().getPostalCode()); // Postal code remains unchanged
        assertEquals("USA", updatedCustomer.get().getCountry()); // Country remains unchanged
    }

    @Test
    public void deleteCustomer() {
        // Arrange
        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("John");
        existingCustomer.setLastName("Doe");
        existingCustomer.setEmail("john.doe@example.com");
        existingCustomer.setState("NY");
        customerRepository.save(existingCustomer);

        // Act
        customerRepository.deleteById(existingCustomer.getId());

        // Assert
        Optional<Customer> deletedCustomer = customerRepository.findById(existingCustomer.getId());
        assertFalse(deletedCustomer.isPresent());
    }

    @Test
    public void findCustomerById() {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setState("NY");
        customer = customerRepository.save(customer);

        // Act
        Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());

        // Assert
        assertTrue(foundCustomer.isPresent()); // Verify if the customer was found
        assertEquals(customer.getId(), foundCustomer.get().getId());
        assertEquals("John", foundCustomer.get().getFirstName());
        assertEquals("Doe", foundCustomer.get().getLastName());
        assertEquals("NY", foundCustomer.get().getState());
    }

    @Test
    public void shouldFindCustomersByState() {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setState("NY");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setState("NY");
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Mike");
        customer3.setLastName("Johnson");
        customer3.setState("CA");
        customerRepository.save(customer3);

        // Act
        List<Customer> customersInNY = customerRepository.findByState("NY");
        List<Customer> customersInCA = customerRepository.findByState("CA");
        List<Customer> customersInTX = customerRepository.findByState("TX");

        // Assert
        assertEquals(2, customersInNY.size());
        assertEquals(1, customersInCA.size());
        assertTrue(customersInNY.contains(customer1));
        assertTrue(customersInNY.contains(customer2));
        assertTrue(customersInCA.contains(customer3));
        assertTrue(customersInTX.isEmpty());
    }
}