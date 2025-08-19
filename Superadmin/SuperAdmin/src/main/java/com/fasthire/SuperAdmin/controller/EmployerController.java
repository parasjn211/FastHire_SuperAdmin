package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.Employer;
import com.fasthire.SuperAdmin.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employer") // base path for all employer APIs
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    // ✅ Only SuperAdmin can create Employer
    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Employer createEmployer(@RequestBody Employer employer) {
        return employerService.saveEmployer(employer);
    }

    // ✅ Accessible by SuperAdmin (to manage employers)
    @GetMapping("/all")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    // ✅ Accessible by SuperAdmin and Employer (employer can fetch own details)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','EMPLOYER')")
    public Optional<Employer> getEmployerById(@PathVariable Long id) {
        return employerService.getEmployerById(id);
    }

    // ✅ Accessible by SuperAdmin
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Optional<Employer> getByEmail(@PathVariable String email) {
        return employerService.getByEmail(email);
    }

    // ✅ Accessible by SuperAdmin
    @GetMapping("/search/{keyword}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<Employer> searchByCompanyName(@PathVariable String keyword) {
        return employerService.searchByCompanyName(keyword);
    }

    // ✅ Accessible by everyone (if you want to show approved employers publicly)
    @GetMapping("/approved")
    @PreAuthorize("hasAnyRole('SUPERADMIN','EMPLOYER','USER')")
    public List<Employer> getApprovedEmployers() {
        return employerService.getApprovedEmployers();
    }
}
