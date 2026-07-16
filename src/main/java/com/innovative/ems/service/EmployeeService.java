package com.innovative.ems.service;

import com.innovative.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    Employee findById(int id);

    List<Employee> findAll();

    void update(Employee employee);

    boolean delete(int id);


}