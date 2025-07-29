package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPServiceimpl implements OTPService {

    private final Map<String, Integer> otpStorage = new HashMap<>();

    @Autowired
    EmailsendService emailsendService;

    @Override
    public void generateAndSendOTP(String email) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
        otpStorage.put(email, otp);

        String message = String.format(
                "Dear User,\n\n" +
                        "Your One-Time Password (OTP) for login is: %d\n" +
                        "This OTP is valid for 5 minutes. Please do not share this OTP with anyone.\n\n" +
                        "If you did not request this, please ignore this email.\n\n" +
                        "Best Regards,\n" +
                        "PJSoftTech Support Team",
                otp
        );

        String subject = "Your OTP for Secure Login - PJSoftTech";

        emailsendService.sendMail(email, subject, message);
    }


    @Override
    public boolean verifyOTP(String email, int otp) {
        if (!otpStorage.containsKey(email)) {
            return false;
        }
        int storedOtp = otpStorage.get(email);
        if (storedOtp == otp) {
            otpStorage.remove(email);
            return true;
        }
        return false;
    }



}
