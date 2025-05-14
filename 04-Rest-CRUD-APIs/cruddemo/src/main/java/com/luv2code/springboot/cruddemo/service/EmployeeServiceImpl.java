package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDao;

    public EmployeeServiceImpl(EmployeeDAO theEmployeeDao){
        employeeDao = theEmployeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(int theId) {
        return employeeDao.findById(theId);
    }

    @Transactional
    @Override
    public Employee save(Employee emp) {
        return employeeDao.save(emp);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeDao.deleteById(theId);
    }
}
