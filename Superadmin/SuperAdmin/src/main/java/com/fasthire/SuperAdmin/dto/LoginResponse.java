package com.fasthire.SuperAdmin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;    // Optional: EMPLOYER / EMPLOYEE
    private String message;
}

