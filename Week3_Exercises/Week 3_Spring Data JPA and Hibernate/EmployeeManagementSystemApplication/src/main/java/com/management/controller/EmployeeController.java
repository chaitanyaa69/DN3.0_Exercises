package com.management.controller;

import com.management.projection.EmployeeProjection;
import com.management.repository.primary.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Endpoint to get all employee projections
    @GetMapping("/projections")
    public List<EmployeeProjection> getEmployeeProjections() {
        return employeeRepository.findEmployeeProjections();
    }

    // Endpoint to get employee projection by ID
    @GetMapping("/projections/{id}")
    public ResponseEntity<EmployeeProjection> getEmployeeProjectionById(@PathVariable Long id) {
        Optional<EmployeeProjection> employeeProjection = employeeRepository.findEmployeeProjectionById(id);
        return employeeProjection.isPresent() ? ResponseEntity.ok(employeeProjection.get()) : ResponseEntity.notFound().build();
    }
}
