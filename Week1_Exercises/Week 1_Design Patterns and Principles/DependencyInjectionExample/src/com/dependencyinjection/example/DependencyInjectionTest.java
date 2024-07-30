package com.dependencyinjection.example;

import com.dependencyinjection.example.repository.CustomerRepository;
import com.dependencyinjection.example.repository.CustomerRepositoryImpl;
import com.dependencyinjection.example.service.CustomerService;

public class DependencyInjectionTest {
    public static void main(String[] args) {
        // Inject CustomerRepository into CustomerService
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);

        // Use CustomerService to find a customer
        String customer = customerService.getCustomer("1");
        System.out.println("Found Customer: " + customer);
    }
}
