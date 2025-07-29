package com.fasthire.SuperAdmin.serviceimpl;


import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.repository.SuperAdminRepository;
import com.fasthire.SuperAdmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewSuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminRepository newSuperAdminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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