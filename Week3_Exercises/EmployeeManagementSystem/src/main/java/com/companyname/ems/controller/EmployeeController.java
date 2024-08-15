package com.companyname.ems.controller;

import com.companyname.ems.dto.EmployeeSummaryDTO;
import com.companyname.ems.projection.CustomEmployeeProjection;
import com.companyname.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/projections/custom-details")
    public ResponseEntity<List<CustomEmployeeProjection>> getCustomEmployeeDetails() {
        List<CustomEmployeeProjection> projections = employeeService.getCustomEmployeeDetails();
        return ResponseEntity.ok(projections);
    }

    @GetMapping("/projections/summary/{departmentId}")
    public ResponseEntity<List<EmployeeSummaryDTO>> getEmployeeSummariesByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeSummaryDTO> summaries = employeeService.getEmployeeSummariesByDepartmentId(departmentId);
        return ResponseEntity.ok(summaries);
    }
}
