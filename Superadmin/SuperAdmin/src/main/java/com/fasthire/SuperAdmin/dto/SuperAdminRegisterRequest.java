package com.fasthire.SuperAdmin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuperAdminRegisterRequest {
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String adminName;
    private String mobileNumber;
    private String address;
    private String city;
    private String state;
    private String registrationNumber;
    private Long aadhar;
    private String pancard;
    private String country;
}
