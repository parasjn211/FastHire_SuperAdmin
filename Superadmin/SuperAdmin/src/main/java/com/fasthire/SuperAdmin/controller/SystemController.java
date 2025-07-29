package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
import com.fasthire.SuperAdmin.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")
public class SystemController
{
    @Autowired
    SystemService systemService;


    @PostMapping("/createsystemwithfeatures")
    public SuperAdminSystem createSystem(@RequestBody Map<String, Object> request) {
        String systemName = request.get("systemName").toString();
        List<Integer> featureIdsInt = (List<Integer>) request.get("featureIds");
        List<Long> featureIds = featureIdsInt.stream().map(Integer::longValue).toList();

        return systemService.createSystemWithFeatures(systemName, featureIds);
    }

    @GetMapping("/getallSystems")
    public List<SystemDTO> getAllSystems() {
        return systemService.getAllSystems();
    }

    @GetMapping("/getSystemby/{id}")
    public ResponseEntity<SuperAdminSystem> getSystemById(@PathVariable Long id) {
        Optional<SuperAdminSystem> system = systemService.getSystemById(id);
        return system.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/updatesystem/{id}")
    public ResponseEntity<SuperAdminSystem> updateSystem(@PathVariable Long id, @RequestBody SuperAdminSystem systemDetails) {
        SuperAdminSystem updatedSystem = systemService.updateSystem(id, systemDetails);
        if (updatedSystem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedSystem);
    }

    // DELETE: Delete a system by ID
    @DeleteMapping("/deletesystem/{id}")
    public ResponseEntity<Void> deleteSystem(@PathVariable Long id) {
        boolean isDeleted = systemService.deleteSystem(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
//
//    @GetMapping("/getFeatureBySystem")
//    public ResponseEntity<SuperAdminSystem> getSystemNameWithFeatures(@RequestParam String systemName) {
//        Optional<SuperAdminSystem> system = systemService.getSystemNameWithFeatures(systemName);
//        return system.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

}
