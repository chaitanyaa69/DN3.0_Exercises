package com.companyname.ems.dto;

public class EmployeeSummaryDTO {

    private String name;
    private String emailWithDepartment;

    // Constructor using name and a custom string combining email and department name
    public EmployeeSummaryDTO(String name, String email, String departmentName) {
        this.name = name;
        this.emailWithDepartment = email + " (" + departmentName + ")";
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailWithDepartment() {
        return emailWithDepartment;
    }

    public void setEmailWithDepartment(String emailWithDepartment) {
        this.emailWithDepartment = emailWithDepartment;
    }
}
