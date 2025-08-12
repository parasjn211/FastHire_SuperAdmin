package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.JWT.JwtUtil;
import com.fasthire.SuperAdmin.dto.LoginRequest;
import com.fasthire.SuperAdmin.dto.LoginResponse;
import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor

public class AuthService {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Login for SuperAdmin
    public LoginResponse loginSuperAdmin(LoginRequest request) {
        Optional<SuperAdmin> admin = superAdminRepository.findByEmailAddress(request.getEmail());

        if (admin.isPresent() && passwordEncoder.matches(request.getPassword(), admin.get().getPassword())) {
            String token = jwtUtil.generateToken(request.getEmail());
            return new LoginResponse(token, "SUPER_ADMIN", "SuperAdmin Login Successful");

        }
        throw new RuntimeException("Invalid SuperAdmin credentials");
    }


}
