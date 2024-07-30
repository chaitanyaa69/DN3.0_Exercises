package com.warehouse.service;

import com.warehouse.model.Product;

public class InventoryTest {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        Product p1 = new Product("P001", "Product1", 100, 10.0);
        Product p2 = new Product("P002", "Product2", 150, 20.0);

        ims.addProduct(p1);
        ims.addProduct(p2);

        System.out.println("Added Products:");
        System.out.println(ims.getProduct("P001").getProductName());
        System.out.println(ims.getProduct("P002").getProductName());

        p1.setPrice(12.0);
        ims.updateProduct(p1);

        System.out.println("Updated Product1 Price: " + ims.getProduct("P001").getPrice());

        ims.deleteProduct("P002");
        System.out.println("Deleted Product2. Is Product2 in inventory? " + (ims.getProduct("P002") != null));
    }
}
