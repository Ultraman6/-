package com.wust.xyz.service;

import com.wust.xyz.mapper.DepartmentMapper;
import com.wust.xyz.mapper.EmployeeMapper;
import com.wust.xyz.model.Department;
import com.wust.xyz.model.Employee;
import com.wust.xyz.utils.EmployeeDTO;
import com.wust.xyz.utils.EmployeePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    // Method for finding paginated and optionally searched employees
    public EmployeePage getPaginatedEmployees(int page, int size, String username, Integer age, String sex, String departmentName) {
        int offset = (page - 1) * size;
        List<Map<String, Object>> results = employeeMapper.searchEmployees(username, age, sex, departmentName, offset, size);

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Map<String, Object> result : results) {
            Employee employee = new Employee();
            // Map fields from result to employee
            System.out.println(result.get("id"));
            Long id = (Long) result.get("id");
            employee.setId(id.intValue());
            employee.setUsername((String) result.get("username"));
            employee.setAge((Integer) result.get("age"));
            employee.setSex((String) result.get("sex"));
            // ... map other Employee fields ...

            String deptName = (String) result.get("departmentName");
            employeeDTOs.add(new EmployeeDTO(employee, deptName));
        }

        int totalRecords = employeeMapper.countSearchEmployees(username, age, sex, departmentName);
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        List<Department> depts = departmentMapper.selectAllDepartments();
        return new EmployeePage(employeeDTOs, page, totalPages, depts);
    }







    // Method for adding an employee
    public void addEmployee(Employee employee) {
        System.out.println(employee);
        employeeMapper.insert(employee);
    }

    // Method for deleting an employee
    public void deleteEmployee(int id) {
        employeeMapper.delete(id);
    }
    public void deleteByDepartmentId(int departmentId) {
        employeeMapper.deleteByDepartmentId(departmentId);
    }
    // Additional business methods
}
