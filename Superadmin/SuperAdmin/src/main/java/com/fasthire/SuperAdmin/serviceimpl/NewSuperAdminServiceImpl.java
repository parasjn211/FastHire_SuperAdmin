package com.fasthire.SuperAdmin.serviceimpl;


import com.fasthire.SuperAdmin.dto.SuperAdminRegisterRequest;
import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.repository.SuperAdminRepository;
import com.fasthire.SuperAdmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class NewSuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminRepository newSuperAdminRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public SuperAdmin registerSuperAdmin(SuperAdminRegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Registration request must not be null");
        }

        // check if email already exists
        Optional<SuperAdmin> existing = newSuperAdminRepository.findByEmailAddress(request.getEmailAddress());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered: " + request.getEmailAddress());
        }

        // map DTO -> entity
        SuperAdmin admin = new SuperAdmin();
        admin.setEmailAddress(request.getEmailAddress());
        admin.setPhoneNumber(request.getPhoneNumber());
        // encode password
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setAdminName(request.getAdminName());
        admin.setMobileNumber(request.getMobileNumber());
        admin.setAddress(request.getAddress());
        admin.setCity(request.getCity());
        admin.setState(request.getState());
        admin.setRegistrationNumber(request.getRegistrationNumber());
        admin.setAadhar(request.getAadhar());
        admin.setPancard(request.getPancard());
        admin.setCountry(request.getCountry());

        return newSuperAdminRepository.save(admin);
    }


    @Override
    public SuperAdmin createNewSuperAdmin(SuperAdmin newSuperAdmin) {
        // Encrypt password before saving
        String encryptedPassword = passwordEncoder.encode(newSuperAdmin.getPassword());
        newSuperAdmin.setPassword(encryptedPassword);

        return newSuperAdminRepository.save(newSuperAdmin);
    }

    @Override
    public SuperAdmin updateNewSuperAdmin(Long id, SuperAdmin updatedSuperAdmin) {
        Optional<SuperAdmin> existingSuperAdminOpt = newSuperAdminRepository.findById(id);

        if (existingSuperAdminOpt.isPresent()) {
            SuperAdmin existingSuperAdmin = existingSuperAdminOpt.get();
            existingSuperAdmin.setEmailAddress(updatedSuperAdmin.getEmailAddress());
            existingSuperAdmin.setPhoneNumber(updatedSuperAdmin.getPhoneNumber());
            if (updatedSuperAdmin.getPassword() != null) {
                String encryptedPassword = passwordEncoder.encode(updatedSuperAdmin.getPassword());
                existingSuperAdmin.setPassword(encryptedPassword);
            }
            existingSuperAdmin.setAdminName(updatedSuperAdmin.getAdminName());
            existingSuperAdmin.setMobileNumber(updatedSuperAdmin.getMobileNumber());
            existingSuperAdmin.setAddress(updatedSuperAdmin.getAddress());
            existingSuperAdmin.setCity(updatedSuperAdmin.getCity());
            existingSuperAdmin.setState(updatedSuperAdmin.getState());
            existingSuperAdmin.setRegistrationNumber(updatedSuperAdmin.getRegistrationNumber());
            existingSuperAdmin.setAadhar(updatedSuperAdmin.getAadhar());
            existingSuperAdmin.setPancard(updatedSuperAdmin.getPancard());
            existingSuperAdmin.setCountry(updatedSuperAdmin.getCountry());


            return newSuperAdminRepository.save(existingSuperAdmin);
        } else {
            throw new RuntimeException("NewSuperAdmin not found with id: " + id);
        }
    }
}