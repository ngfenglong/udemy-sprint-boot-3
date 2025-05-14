package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // quick and dirty: inject employee dao
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for Get /employees/(employeeId)
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployees(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee UpdateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){
        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id is not allowed in request body - " + employeeId);
        }

        Employee patchedEmployee = apply(patchPayload, tempEmployee);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;

    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {
        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
