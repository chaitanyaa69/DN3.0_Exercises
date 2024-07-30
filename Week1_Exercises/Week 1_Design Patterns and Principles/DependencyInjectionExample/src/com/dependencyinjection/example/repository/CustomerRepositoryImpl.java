package com.dependencyinjection.example.repository;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static Map<String, String> customerData = new HashMap<>();

    static {
        customerData.put("1", "Alice");
        customerData.put("2", "Bob");
    }

    @Override
    public String findCustomerById(String customerId) {
        return customerData.get(customerId);
    }
}
