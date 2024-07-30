package com.ecommerce.sort;

import com.ecommerce.model.Order;

public class SortTest {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 250.0),
            new Order("O002", "Bob", 150.0),
            new Order("O003", "Charlie", 300.0),
            new Order("O004", "David", 200.0),
            new Order("O005", "Eve", 100.0)
        };

        // Test Bubble Sort
        BubbleSort.bubbleSort(orders);
        System.out.println("Orders sorted by Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order.getCustomerName() + ": $" + order.getTotalPrice());
        }

        // Test Quick Sort
        orders = new Order[] {
            new Order("O001", "Alice", 250.0),
            new Order("O002", "Bob", 150.0),
            new Order("O003", "Charlie", 300.0),
            new Order("O004", "David", 200.0),
            new Order("O005", "Eve", 100.0)
        };
        QuickSort.quickSort(orders, 0, orders.length - 1);
        System.out.println("Orders sorted by Quick Sort:");
        for (Order order : orders) {
            System.out.println(order.getCustomerName() + ": $" + order.getTotalPrice());
        }
    }
}