package com.companyname.ems.repository;

import com.companyname.ems.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find all departments with pagination and sorting
    Page<Department> findAll(Pageable pageable);
}
