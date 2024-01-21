package com.wust.xyz.mapper;


import com.wust.xyz.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    @Delete("DELETE FROM employees WHERE deptid = #{departmentId}")
    void deleteByDepartmentId(int departmentId);

    @Select("SELECT e.*, d.dname AS departmentName FROM employees e LEFT JOIN dept d ON e.deptid = d.id")
    List<Employee> findPaginated(@Param("offset") int offset, @Param("pageSize") int pageSize);



    @Select("<script>" +
            "SELECT COUNT(*) FROM employees e " +
            "LEFT JOIN dept d ON e.deptid = d.id " +
            "<where> " +
            "<if test='username != null and username.trim() != \"\"'>AND e.username LIKE CONCAT('%', #{username}, '%') </if> " +
            "<if test='age != null'>AND e.age = #{age} </if> " +
            "<if test='sex != null and sex.trim() != \"\"'>AND e.sex = #{sex} </if> " +
            "<if test='departmentName != null and departmentName.trim() != \"\"'>AND d.dname LIKE CONCAT('%', #{departmentName}, '%') </if> " +
            "</where> " +
            "</script>")
    int countSearchEmployees(@Param("username") String username,
                             @Param("age") Integer age,
                             @Param("sex") String sex,
                             @Param("departmentName") String departmentName);


    // SQL for inserting a new employee
    @Insert("INSERT INTO employees (username, age, sex, deptid) VALUES (#{username}, #{age}, #{sex}, #{deptid})")
    void insert(Employee employee);

    // SQL for deleting an employee by ID
    @Delete("DELETE FROM employees WHERE id = #{id}")
    void delete(int id);

    @Select("<script>" +
            "SELECT e.*, d.dname AS departmentName FROM employees e " +
            "LEFT JOIN dept d ON e.deptid = d.id " +
            "<where> " +
            "<if test='username != null and username.trim() != \"\"'>AND e.username LIKE CONCAT('%', #{username}, '%') </if> " +
            "<if test='age != null'>AND e.age = #{age} </if> " +
            "<if test='sex != null and sex.trim() != \"\"'>AND e.sex = #{sex} </if> " +
            "<if test='departmentName != null and departmentName.trim() != \"\"'>AND d.dname LIKE CONCAT('%', #{departmentName}, '%') </if> " +
            "</where> " +
            "LIMIT #{pageSize} OFFSET #{offset}" +
            "</script>")
    List<Map<String, Object>> searchEmployees(@Param("username") String username,
                                              @Param("age") Integer age,
                                              @Param("sex") String sex,
                                              @Param("departmentName") String departmentName,
                                              @Param("offset") int offset,
                                              @Param("pageSize") int pageSize);




}
