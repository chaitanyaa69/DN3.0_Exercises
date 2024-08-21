package com.example.bookstore;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>();

    // POST method to create a new customer using JSON request body
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customerList.add(customer);
        return customer;
    }
 // POST method to process form data for customer registration
    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam("name") String name,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password) {
        Customer customer = new Customer(null, name, email, password);
        customerList.add(customer);
        return customer;
    }
 // GET method to retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                customerList.remove(customer);
                return "Customer with ID " + id + " has been deleted.";
            }
        }
        return "Customer with ID " + id + " not found.";
    }


}

