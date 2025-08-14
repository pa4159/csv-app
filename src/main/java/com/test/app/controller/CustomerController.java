package com.test.app.controller;

import com.test.app.model.Customer;
import com.test.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping(value = "/customer/{customerReference}")
    public Optional<Customer> getCustomer(@PathVariable String customerReference) {
        return repository.findById(customerReference);
    }

    @PostMapping("/customer")
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

}
