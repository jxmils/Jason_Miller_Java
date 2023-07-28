package com.company.customerdataservice.repository;
import com.company.customerdataservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Method to filter customers by state
    List<Customer> findByState(String state);

    // Method to find a specific customer by id
    Optional<Customer> findById(Integer id);

    // Method to delete a customer by id
    void deleteById(Integer id);
}