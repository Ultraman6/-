package com.wust.xyz.utils;

import com.wust.xyz.model.Employee;

public class EmployeeDTO {
    public Employee employee;
    public String departmentName;
    public EmployeeDTO(Employee employee, String departmentName) {
        this.employee = employee;
        this.departmentName = departmentName;
    }
}
