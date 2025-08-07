package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.repository.EmployerRepository;
import com.fasthire.SuperAdmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employerRepository.findByEmail(email)
                .map(emp -> User.withUsername(emp.getEmail())
                        .password(emp.getPassword())
                        .roles("EMPLOYER")
                        .build())
                .orElseGet(() -> userRepository.findByEmail(email)
                        .map(user -> User.withUsername(user.getEmail())
                                .password(user.getPassword())
                                .roles("EMPLOYEE") // or "USER" depending on your naming
                                .build())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email)));
    }
}
