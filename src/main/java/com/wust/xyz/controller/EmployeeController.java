package com.wust.xyz.controller;

import com.wust.xyz.model.Employee;
import com.wust.xyz.service.EmployeeService;
import com.wust.xyz.utils.EmployeePage;
import com.wust.xyz.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET endpoint for listing employees with search and pagination
    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) Integer age,
                                @RequestParam(required = false) String sex,
                                @RequestParam(required = false) String departmentName) {
        EmployeePage employeePage = employeeService.getPaginatedEmployees(page, 3, username, age, sex, departmentName);
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("searchCriteria", new SearchCriteria(username, age, sex, departmentName));
        return "employees";
    }


    // POST endpoint for adding a new employee
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        System.out.println(employee.getDeptid());
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    // POST endpoint for deleting an employee
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}