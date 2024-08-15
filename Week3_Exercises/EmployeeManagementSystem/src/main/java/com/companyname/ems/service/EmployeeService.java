package com.companyname.ems.service;

import com.companyname.ems.dto.EmployeeSummaryDTO;
import com.companyname.ems.projection.CustomEmployeeProjection;
import com.companyname.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<CustomEmployeeProjection> getCustomEmployeeDetails() {
        return employeeRepository.findCustomEmployeeDetails();
    }

    public List<EmployeeSummaryDTO> getEmployeeSummariesByDepartmentId(Long departmentId) {
        return employeeRepository.findEmployeeSummariesByDepartmentId(departmentId);
    }
}
