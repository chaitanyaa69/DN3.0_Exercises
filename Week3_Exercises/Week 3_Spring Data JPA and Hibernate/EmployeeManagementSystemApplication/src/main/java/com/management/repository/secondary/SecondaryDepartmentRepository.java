package com.management.repository.secondary;

import com.management.model.primary.Department;
import com.management.projection.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface SecondaryDepartmentRepository extends JpaRepository<Department, Long> {

    // Query method to get all department projections
    @Query("SELECT d.id AS id, d.name AS name FROM Department d")
    List<DepartmentProjection> findDepartmentProjections();

    // Query method to get a department projection by ID
    @Query("SELECT d.id AS id, d.name AS name FROM Department d WHERE d.id = :id")
    Optional<DepartmentProjection> findDepartmentProjectionById(Long id);
}
