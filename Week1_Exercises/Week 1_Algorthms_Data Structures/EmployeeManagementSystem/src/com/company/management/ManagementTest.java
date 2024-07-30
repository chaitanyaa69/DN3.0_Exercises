package com.company.management;

import com.company.model.Employee;

public class ManagementTest {
    public static void main(String[] args) {
        EmployeeManagement em = new EmployeeManagement(5);

        Employee e1 = new Employee("E001", "Alice", "Manager", 75000);
        Employee e2 = new Employee("E002", "Bob", "Engineer", 50000);
        Employee e3 = new Employee("E003", "Charlie", "Technician", 40000);

        em.addEmployee(e1);
        em.addEmployee(e2);
        em.addEmployee(e3);

        System.out.println("All Employees:");
        em.traverseEmployees();

        System.out.println("\nSearching for Employee with ID E002:");
        Employee found = em.searchEmployee("E002");
        if (found != null) {
            System.out.println(found.getName() + " - " + found.getPosition() + " - $" + found.getSalary());
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting Employee with ID E002:");
        em.deleteEmployee("E002");

        System.out.println("\nAll Employees after deletion:");
        em.traverseEmployees();
    }
}
