package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureAssignDTO;
import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureDTO;
import com.fasthire.SuperAdmin.service.PlanAdditionalFeatureService;
import com.fasthire.SuperAdmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")

public class PlanAdditionalFeatureController {

    @Autowired
    private PlanAdditionalFeatureService planAdditionalFeatureService;

    @Autowired
    private PlanService planService;

    @PostMapping("/assignValueToAddFeature")
    public ResponseEntity<List<PlanAdditionalFeatureDTO>> assignFeaturesToPlan(
            @RequestBody PlanAdditionalFeatureAssignDTO dto) {
        List<PlanAdditionalFeatureDTO> assignedFeatures = planAdditionalFeatureService.assignAdditionalFeaturesToPlan(dto);
        return ResponseEntity.ok(assignedFeatures);
    }


    @GetMapping("/getAddFeatureBy/{planId}")
    public List<PlanAdditionalFeatureDTO> getFeaturesByPlan(@PathVariable Long planId) {
        return planAdditionalFeatureService.getFeaturesByPlan(planId);
    }

    @PutMapping("/updateAddFeatureValue/{id}")
    public ResponseEntity<String> updateFeatureStatus(
            @RequestParam Long planId,
            @RequestParam Long featureId,
            @RequestParam boolean enabled) {

        planAdditionalFeatureService.updateFeatureStatus(planId, featureId, enabled);
        return ResponseEntity.ok("Feature status updated.");
    }

    @DeleteMapping("/deleteAddFeaturevalue/{id}")
    public String deleteFeatureAssignment(@PathVariable Long id) {
        planAdditionalFeatureService.deleteFeatureAssignment(id);
        return "Feature assignment deleted successfully";
    }
}