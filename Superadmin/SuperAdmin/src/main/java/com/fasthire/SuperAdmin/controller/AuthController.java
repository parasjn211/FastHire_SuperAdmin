package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.LoginRequest;
import com.fasthire.SuperAdmin.dto.LoginResponse;
import com.fasthire.SuperAdmin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
////@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://pjsofttech.in")
@RequiredArgsConstructor
public class AuthController {

    private  AuthService authService;

    @PostMapping("/superadminlogin")
    public ResponseEntity<LoginResponse> loginSuperAdmin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginSuperAdmin(request));
    }


}