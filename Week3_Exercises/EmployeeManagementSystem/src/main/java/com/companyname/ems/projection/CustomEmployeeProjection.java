package com.companyname.ems.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CustomEmployeeProjection {

    String getName();

    @Value("#{target.email + ' (' + target.department.name + ')'}")
    String getEmailWithDepartment();
}
