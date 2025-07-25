package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.dto.LoginRequest;
import com.fasthire.SuperAdmin.dto.LoginResponse;
import com.fasthire.SuperAdmin.entity.Employee;
import com.fasthire.SuperAdmin.entity.Employer;
import com.fasthire.SuperAdmin.service.CustomUserDetailsService;
import com.fasthire.SuperAdmin.service.EmployeeService;
import com.fasthire.SuperAdmin.service.EmployerService;
import com.fasthire.SuperAdmin.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private CustomUserDetailsService userDetailsService;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EmployerService employerService;
    @Autowired private EmployeeService employeeService;

    @PostMapping("/employer/register")
    public String registerEmployer(@RequestBody Employer employer) {
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        employerService.saveEmployer(employer);
        return "Employer registered successfully";
    }

    @PostMapping("/employee/register")
    public String registerEmployee(@RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.saveEmployee(employee);
        return "Employee registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails.getUsername());

        String role = userDetails.getAuthorities().stream()
                .findFirst().map(GrantedAuthority::getAuthority).orElse("USER");

        return new LoginResponse(token, role, "Login successful");
    }

}

