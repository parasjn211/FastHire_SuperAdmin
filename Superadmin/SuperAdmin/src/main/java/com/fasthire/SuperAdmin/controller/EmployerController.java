package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.Employer;
import com.fasthire.SuperAdmin.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/createEmployer")
    public Employer createEmployer(@RequestBody Employer employer) {
        return employerService.saveEmployer(employer);
    }

    @GetMapping("/getAllEmployers")
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @GetMapping("/getEmployerById/{id}")
    public Optional<Employer> getEmployerById(@PathVariable Long id) {
        return employerService.getEmployerById(id);
    }

    @GetMapping("/getEmployerByEmail/{email}")
    public Optional<Employer> getByEmail(@PathVariable String email) {
        return employerService.getByEmail(email);
    }

    @GetMapping("/searchByCompanyName/{keyword}")
    public List<Employer> searchByCompanyName(@PathVariable String keyword) {
        return employerService.searchByCompanyName(keyword);
    }

    @GetMapping("/getApprovedEmployers")
    public List<Employer> getApprovedEmployers() {
        return employerService.getApprovedEmployers();
    }
}

