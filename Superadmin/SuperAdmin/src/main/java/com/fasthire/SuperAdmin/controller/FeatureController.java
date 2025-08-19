package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.SuperAdminFeature;
import com.fasthire.SuperAdmin.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/createFeature")
    public SuperAdminFeature createFeature(@RequestBody SuperAdminFeature feature) {
        return featureService.createFeature(feature);
    }

    @PutMapping("/updateFeature/{id}")
    public ResponseEntity<SuperAdminFeature> updateFeature(@PathVariable Long id, @RequestBody SuperAdminFeature superAdminFeature) {
        SuperAdminFeature updatedFeature = featureService.updateFeature(id, superAdminFeature);
        return updatedFeature != null ? ResponseEntity.ok(updatedFeature) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteFeature/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/AllFeatures")
    public ResponseEntity<List<SuperAdminFeature>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAllFeatures());
    }

    @GetMapping("/FeatureById/{id}")
    public ResponseEntity<SuperAdminFeature> getFeatureById(@PathVariable Long id) {
        Optional<SuperAdminFeature> feature = featureService.getFeatureById(id);
        return feature.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}