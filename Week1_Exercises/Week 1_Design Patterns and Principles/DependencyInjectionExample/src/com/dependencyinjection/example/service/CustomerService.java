package com.dependencyinjection.example.service;

import com.dependencyinjection.example.repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomer(String customerId) {
        return customerRepository.findCustomerById(customerId);
    }
}
