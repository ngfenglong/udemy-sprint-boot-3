package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDao;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDao, EmployeeRepository theEmployeeRepository){
        employeeDao = theEmployeeDao;
        employeeRepository = theEmployeeRepository;
    }


    //region DAO Approach
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
    //endregion

    //region JPARepository Approach
    @Override
    public List<Employee> findAllByRepo() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByIdByRepo(int theId) {
        Optional<Employee> result =  employeeRepository.findById(theId);
        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        }

        else{
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee saveByRepo(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public void deleteByIdByRepo(int theId) {
        employeeRepository.deleteById(theId);
    }
    //endregion
}
