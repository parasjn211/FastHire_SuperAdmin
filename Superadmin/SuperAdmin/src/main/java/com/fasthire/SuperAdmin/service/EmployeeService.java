package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> getByEmail(String email);
    List<Employee> getByLocation(String location);
    List<Employee> getByEducation(String education);
}

