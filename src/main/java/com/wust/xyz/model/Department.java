package com.wust.xyz.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Department.java
public class Department {

    @Getter
    private int id;
    @Getter
    @Setter
    private String dname;

    // List of employees in the department
    @Getter
    @Setter
    private List<Employee> employees;

}
