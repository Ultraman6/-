package com.wust.xyz.mapper;

import com.wust.xyz.model.Department;
import com.wust.xyz.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

// DepartmentMapper.java
@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM dept WHERE id = #{id}")
    Department findById(int id);

    // Additional MyBatis annotations for CRUD operations on departments
    @Select("SELECT * FROM dept")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "employees", column = "id", many = @Many(select = "selectEmployeesByDepartment"))
    })
    List<Department> selectAllDepartmentsWithEmployees();
    @Select("SELECT * FROM dept")
    List<Department> selectAllDepartments();
    @Select("SELECT * FROM employees WHERE deptid = #{deptId}")
    List<Employee> selectEmployeesByDepartment(int deptId);
    // In DepartmentMapper.java
    @Insert("INSERT INTO dept (dname) VALUES (#{dname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertDept(Department department);

    // In EmployeeMapper.java
    @Insert({
            "<script>",
            "INSERT INTO employees (username, age, sex, deptid) VALUES",
            "<foreach collection='employees' item='employee' separator=','>",
            "(#{employee.username}, #{employee.age}, #{employee.sex}, #{employee.deptId})",
            "</foreach>",
            "</script>"
    })
    void insertEmployees(@Param("employees") List<Employee> employees);

    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteDept(int id);


}
