package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.LoginRequest;
import com.fasthire.SuperAdmin.dto.LoginResponse;
import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.repository.SuperAdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthService {

    private  SuperAdminRepository superAdminRepository;
    private  PasswordEncoder passwordEncoder;
    private  JwtUtil jwtUtil;

    // Login for SuperAdmin
    public LoginResponse loginSuperAdmin(LoginRequest request) {
        Optional<SuperAdmin> admin = superAdminRepository.findByEmailAddress(request.getEmail());

        if (admin.isPresent() && passwordEncoder.matches(request.getPassword(), admin.get().getPassword())) {
            String token = jwtUtil.generateToken(request.getEmail());
            return new LoginResponse(token, "SuperAdmin Login Successful");
        }
        throw new RuntimeException("Invalid SuperAdmin credentials");
    }


}
