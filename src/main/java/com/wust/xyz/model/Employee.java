package com.wust.xyz.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;


public class Employee {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private int age;
    @Getter
    @Setter
    private String sex;
    @Getter
    @Setter
    private int deptid;
    private String departmentName;
//    public Employee(String username, int age, String sex, int deptid) {
//        this.username = username;
//        this.age = age;
//        this.sex = sex;
//        this.deptid = deptid;
//    }
// Getters and setters
}
