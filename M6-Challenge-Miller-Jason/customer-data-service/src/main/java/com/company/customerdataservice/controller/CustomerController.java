package com.company.customerdataservice.controller;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("customer/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            // Update the fields of the existingCustomer with the updatedCustomer data
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setCompany(updatedCustomer.getCompany());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setAddress1(updatedCustomer.getAddress1());
            existingCustomer.setAddress2(updatedCustomer.getAddress2());
            existingCustomer.setCity(updatedCustomer.getCity());
            existingCustomer.setState(updatedCustomer.getState());
            existingCustomer.setPostalCode(updatedCustomer.getPostalCode());
            existingCustomer.setCountry(updatedCustomer.getCountry());

            return customerRepository.save(existingCustomer);
        }
        else {
            return null;
        }
    }

    @DeleteMapping("customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("customer/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

    @GetMapping("/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return customerRepository.findByState(state);
    }
}
