package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.PlanSystemMapRequest;
import com.fasthire.SuperAdmin.dto.SuperAdminPlanSystemDTO;
import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.service.PlanSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")
public class PlanSystemController
{
    @Autowired
    private PlanSystemService planSystemService;



    @GetMapping("/getSystemsby/{planId}")
    public ResponseEntity<List<SuperAdminPlanSystemDTO>> getSystemsByPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(planSystemService.getSystemsByPlan(planId));
    }

    @GetMapping("/getPlanby/{systemId}")
    public ResponseEntity<List<SuperAdminPlanSystemDTO>> getPlansBySystem(@PathVariable Long systemId) {
        return ResponseEntity.ok(planSystemService.getPlansBySystem(systemId));
    }


    @PostMapping("/assignValueForSystem")
    public ResponseEntity<List<SuperAdminPlanSystemDTO>> assignSystemsToPlan(
            @RequestBody PlanSystemMapRequest request) {
        return ResponseEntity.ok(planSystemService.assignSystemsToPlan(request));
    }

    @PutMapping("/updatesystemvalue")
    public ResponseEntity<SuperAdminPlanSystemDTO> updateSystemStatus(
            @RequestParam Long planId,
            @RequestParam Long systemId,
            @RequestParam boolean enabled) {
        return ResponseEntity.ok(planSystemService.updateSystemStatus(planId, systemId, enabled));
    }

    @GetMapping("/getAllSystemsByPlanId")
    public ResponseEntity<List<SystemDTO>> getSystemsByPlanId(@RequestParam Long planId) {
        List<SystemDTO> systems = planSystemService.getSystemsByPlanId(planId);
        return ResponseEntity.ok(systems);
    }


}
