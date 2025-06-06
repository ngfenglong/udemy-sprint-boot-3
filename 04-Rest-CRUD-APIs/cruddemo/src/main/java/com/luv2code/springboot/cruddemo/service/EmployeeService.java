package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee emp);
    void deleteById(int theId);

    List<Employee> findAllByRepo();
    Employee findByIdByRepo(int theId);
    Employee saveByRepo(Employee emp);
    void deleteByIdByRepo(int theId);
}
