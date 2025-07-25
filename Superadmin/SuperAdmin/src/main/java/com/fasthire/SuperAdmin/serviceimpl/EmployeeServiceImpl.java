package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.Employee;
import com.fasthire.SuperAdmin.repository.EmployeeRepository;
import com.fasthire.SuperAdmin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getByLocation(String location) {
        return employeeRepository.findByLocation(location);
    }

    @Override
    public List<Employee> getByEducation(String education) {
        return employeeRepository.findByEducation(education);
    }
}

