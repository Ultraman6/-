package com.wust.xyz.controller;

import com.wust.xyz.model.Department;
import com.wust.xyz.model.Employee;
import com.wust.xyz.service.DepartmentService;
import com.wust.xyz.service.EmployeeService;
import com.wust.xyz.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";    // Refers to departments.html Thymeleaf template
    }

    @GetMapping("/detail")
    public String listDetails(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department_details";    // Refers to departments.html Thymeleaf template
    }

    @PostMapping("/add")
    public String addDepartment(@RequestParam String name, RedirectAttributes redirectAttributes) {
        Department newDepartment = new Department();
        newDepartment.setDname(name);
        // 返回新建部门的id,生成三个随机员工加入
        int flag = departmentService.addDepartment(newDepartment);
        if (flag > 0) {
            // 插入成功，department对象的id属性现在已经被赋值为数据库生成的id
            int did = newDepartment.getId();
            for (int i = 0; i < 3; i++) {
                Employee newEmployee = new Employee();
                newEmployee.setUsername(EmployeeUtils.getRandomName());
                newEmployee.setAge(EmployeeUtils.getRandomAge());
                newEmployee.setSex(EmployeeUtils.getRandomGender());
                newEmployee.setDeptid(did);
                employeeService.addEmployee(newEmployee);
            }
        } else {
            // 插入失败，处理错误或抛出异常
            throw new RuntimeException("Failed to insert department.");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Department added successfully!");
        return "redirect:/departments";
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        departmentService.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("successMessage", "Department deleted successfully!");
        return "redirect:/departments";
    }
}