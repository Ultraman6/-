package com.wust.xyz.service;

import com.wust.xyz.mapper.DepartmentMapper;
import com.wust.xyz.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// DepartmentService.java
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired //一定要加这个，臭婊子
    private EmployeeService employeeService;
    public Department getDepartmentById(int id) {
        return departmentMapper.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentMapper.selectAllDepartmentsWithEmployees();
    }

    public int addDepartment(Department newDepartment) {
        return departmentMapper.insertDept(newDepartment);
    }

    public void deleteDepartment(int id) {
        employeeService.deleteByDepartmentId(id);
        departmentMapper.deleteDept(id);
    }

}
