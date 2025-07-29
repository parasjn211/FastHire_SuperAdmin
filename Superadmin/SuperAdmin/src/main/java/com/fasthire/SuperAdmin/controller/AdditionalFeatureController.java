package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.entity.SuperAdminAdditionalFeature;
import com.fasthire.SuperAdmin.service.AdditionalFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")
public class AdditionalFeatureController
{

    @Autowired
    AdditionalFeatureService additionalFeatureService;

    @PostMapping("/additionalFeature")
    public ResponseEntity<SuperAdminAdditionalFeature> createFeature(@RequestBody SuperAdminAdditionalFeature feature) {
        return ResponseEntity.ok(additionalFeatureService.createFeature(feature));
    }

    @GetMapping("/getAddFeature/{id}")
    public ResponseEntity<SuperAdminAdditionalFeature> getFeatureById(@PathVariable Long id) {
        return ResponseEntity.ok(additionalFeatureService.getFeatureById(id));
    }

    @GetMapping("/getallAddFeature")
    public ResponseEntity<List<SuperAdminAdditionalFeature>> getAllFeatures() {
        return ResponseEntity.ok(additionalFeatureService.getAllFeatures());
    }


    @PutMapping("/updateAddFeature/{id}")
    public ResponseEntity<SuperAdminAdditionalFeature> updateFeature(@PathVariable Long id, @RequestBody SuperAdminAdditionalFeature feature) {
        return ResponseEntity.ok(additionalFeatureService.updateFeature(id, feature));
    }


    @DeleteMapping("/deleteAddFeature/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Long id) {
        additionalFeatureService.deleteFeature(id);
        return ResponseEntity.ok("Feature deleted successfully!");
    }

}
