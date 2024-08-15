package com.management.controller;

import com.management.projection.DepartmentProjection;
import com.management.repository.primary.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/projections")
    public List<DepartmentProjection> getDepartmentProjections() {
        return departmentRepository.findDepartmentProjections();
    }

    @GetMapping("/projections/{id}")
    public ResponseEntity<DepartmentProjection> getDepartmentProjectionById(@PathVariable Long id) {
        Optional<DepartmentProjection> departmentProjection = departmentRepository.findDepartmentProjectionById(id);
        return departmentProjection.isPresent() ? ResponseEntity.ok(departmentProjection.get()) : ResponseEntity.notFound().build();
    }
}
