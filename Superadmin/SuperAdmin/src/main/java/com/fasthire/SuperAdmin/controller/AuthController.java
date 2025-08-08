package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.LoginRequest;
import com.fasthire.SuperAdmin.dto.LoginResponse;
import com.fasthire.SuperAdmin.dto.SuperAdminRegisterRequest;
import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.service.AuthService;
import com.fasthire.SuperAdmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
////@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://pjsofttech.in")
@RequiredArgsConstructor // Lombok will generate a constructor with all final fields
public class AuthController {

    private final AuthService authService;  // final ensures it's initialized in constructor
    private final SuperAdminService superAdminService;

    @PostMapping("/superadmin/register")
    public ResponseEntity<SuperAdmin> registerSuperAdmin(@RequestBody SuperAdminRegisterRequest request) {
        SuperAdmin savedAdmin = superAdminService.registerSuperAdmin(request);
        return ResponseEntity.ok(savedAdmin);
    }

    @PostMapping("/superadminlogin")
    public ResponseEntity<LoginResponse> loginSuperAdmin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginSuperAdmin(request));
    }


}