package com.management.repository.primary;

import com.management.model.primary.Employee;
import com.management.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Query method to get projections
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findEmployeeProjections();

    // Query method to get a projection by ID
    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Optional<EmployeeProjection> findEmployeeProjectionById(Long id);
}
