package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.Employee;
import com.fasthire.SuperAdmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeById/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getByEmail/{email}")
    public Optional<Employee> getByEmail(@PathVariable String email) {
        return employeeService.getByEmail(email);
    }

//    @GetMapping("/getByLocation/{location}")
//    public List<Employee> getByLocation(@PathVariable String location) {
//        return employeeService.getByLocation(location);
//    }

    @GetMapping("/getByEducation/{education}")
    public List<Employee> getByEducation(@PathVariable String education) {
        return employeeService.getByEducation(education);
    }
}
