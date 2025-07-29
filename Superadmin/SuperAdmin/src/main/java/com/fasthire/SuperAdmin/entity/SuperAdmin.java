package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    private String  phoneNumber;
    @Column(nullable = false)
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
