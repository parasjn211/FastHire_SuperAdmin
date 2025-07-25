package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.repository.EmployeeRepository;
import com.fasthire.SuperAdmin.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employerRepository.findByEmail(email)
                .map(emp -> User.withUsername(emp.getEmail()).password(emp.getPassword()).roles("EMPLOYER").build())
                .orElseGet(() -> employeeRepository.findByEmail(email)
                        .map(emp -> User.withUsername(emp.getEmail()).password(emp.getPassword()).roles("EMPLOYEE").build())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email)));
    }
}

