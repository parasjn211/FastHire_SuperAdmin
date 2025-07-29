package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.SuperAdmin;
import com.fasthire.SuperAdmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")
public class NewSuperAdminController {

    @Autowired
    private SuperAdminService newSuperAdminService;

    @PostMapping("/createNewSuperAdmin")
    public ResponseEntity<SuperAdmin> createNewSuperAdmin(@RequestBody SuperAdmin newSuperAdmin) {
        SuperAdmin savedSuperAdmin = newSuperAdminService.createNewSuperAdmin(newSuperAdmin);
        return ResponseEntity.ok(savedSuperAdmin);
    }

    @PutMapping("/updateNewSuperAdmin/{id}")
    public ResponseEntity<SuperAdmin> updateNewSuperAdmin(@PathVariable Long id, @RequestBody SuperAdmin newSuperAdmin) {
        SuperAdmin updatedSuperAdmin = newSuperAdminService.updateNewSuperAdmin(id, newSuperAdmin);
        return ResponseEntity.ok(updatedSuperAdmin);
    }
}