package com.companyname.ems.repository;

import com.companyname.ems.dto.EmployeeSummaryDTO;
import com.companyname.ems.model.Employee;
import com.companyname.ems.projection.CustomEmployeeProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.companyname.ems.dto.EmployeeSummary(e.name, e.email, d.name) " +
           "FROM Employee e JOIN e.department d WHERE d.id = :departmentId")
    List<EmployeeSummaryDTO> findEmployeeSummariesByDepartmentId(@Param("departmentId") Long departmentId);

	List<CustomEmployeeProjection> findCustomEmployeeDetails();
}
