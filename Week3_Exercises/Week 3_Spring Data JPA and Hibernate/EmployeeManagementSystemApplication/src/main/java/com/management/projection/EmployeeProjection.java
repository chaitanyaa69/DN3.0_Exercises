package com.management.projection;

public interface EmployeeProjection {
    
    Long getId();
    
    String getName();
    
    String getEmail();
    
    String getDepartmentName(); // This will require a join fetch
}
