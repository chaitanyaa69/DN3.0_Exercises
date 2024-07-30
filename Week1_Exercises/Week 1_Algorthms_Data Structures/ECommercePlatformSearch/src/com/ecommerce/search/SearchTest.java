package com.ecommerce.search;

import com.ecommerce.model.Product;
import java.util.Arrays;

public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Tablet", "Electronics"),
            new Product("P004", "Headphones", "Accessories"),
            new Product("P005", "Keyboard", "Accessories")
        };

        // Test Linear Search
        Product result = LinearSearch.linearSearch(products, "Tablet");
        System.out.println("Linear Search Result: " + (result != null ? result.getProductName() : "Not Found"));

        // Test Binary Search
        Arrays.sort(products, (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName()));
        result = BinarySearch.binarySearch(products, "iPad");
        System.out.println("Binary Search Result: " + (result != null ? result.getProductName() : "Not Found"));
    }
}