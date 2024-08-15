package com.management.repository.secondary;

import com.management.model.secondary.SecondaryEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryEmployeeRepository extends JpaRepository<SecondaryEmployee, Long> {
}
