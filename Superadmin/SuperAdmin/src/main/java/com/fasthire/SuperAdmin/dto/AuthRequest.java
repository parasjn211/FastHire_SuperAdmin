package com.fasthire.SuperAdmin.dto;

import lombok.Data;

@Data
class AuthRequest {
    private String email;
    private String password;
}
