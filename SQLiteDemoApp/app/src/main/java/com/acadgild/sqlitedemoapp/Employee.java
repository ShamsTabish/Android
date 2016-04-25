package com.acadgild.sqlitedemoapp;

/**
 * Created by ssm2349 on 4/23/16.
 */
public class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id,String name, String department){
        this.id=id;
        this.department=department;
        this.name=name;
    }

    @Override
    public String toString() {
        return id+",\t"+name+",\t"+department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
