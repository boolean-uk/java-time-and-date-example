package com.booleanuk.timey_wimey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(this.customerRepository.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save the customer the following error occurred: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> cetOneCustomer(@PathVariable int id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers with that Id found in the Database")
        );
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer customerToUpdate = this.customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers with that Id found in the Database")
        );
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAppointment(customer.getAppointment());
        customerToUpdate.setUpdatedAt(LocalDateTime.now());
        return new ResponseEntity<>(this.customerRepository.save(customerToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
        Customer customerToDelete = this.customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers with that Id found in the Database")
        );
        this.customerRepository.delete(customerToDelete);
        // Not strictly necessary but here for completeness' sake
        customerToDelete.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(customerToDelete);
    }

}
