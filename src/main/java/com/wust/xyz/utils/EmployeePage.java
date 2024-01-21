package com.wust.xyz.utils;

import com.wust.xyz.model.Department;

import java.util.List;


public class EmployeePage {
    public List<EmployeeDTO> employees;
    public int currentPage;
    public int totalPages;
    public List<Department> departments;
    // Constructor
    public EmployeePage(List<EmployeeDTO> employees, int currentPage, int totalPages, List<Department> departments) {
        this.employees = employees;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.departments = departments;
    }

    // Getters and Setters
}

