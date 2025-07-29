package com.fasthire.SuperAdmin.service;

public interface OTPService {
    void generateAndSendOTP(String email);
    boolean verifyOTP(String email, int otp);

}
