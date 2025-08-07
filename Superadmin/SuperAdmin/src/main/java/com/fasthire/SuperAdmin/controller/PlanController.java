package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.AssignSystemAndFeatureRequest;
import com.fasthire.SuperAdmin.dto.AssignSystemAndFeatureResponse;
import com.fasthire.SuperAdmin.dto.PlanDTO;
import com.fasthire.SuperAdmin.dto.SectionWithPlansDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")

public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/createPlan")
    public ResponseEntity<SuperAdminPlan> createPlan(@RequestBody PlanDTO planDTO) {
        SuperAdminPlan createdPlan = planService.createPlan(planDTO);
        return ResponseEntity.ok(createdPlan);
    }

    @GetMapping("/PlanById/{id}")
    public ResponseEntity<Optional<SuperAdminPlan>> getPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(planService.getPlanById(id));
    }

    @GetMapping("/AllPlans")
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @PutMapping("/updatePlan/{id}")
    public ResponseEntity<SuperAdminPlan> updatePlan(@PathVariable Long id, @RequestBody PlanDTO updatedPlanDTO) {
        SuperAdminPlan updatedPlan = planService.updatePlan(id, updatedPlanDTO);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/deletePlan/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }




//    @GetMapping("/{planId}/features")
//    public Map<String, Object> getPlanWithFeatures(@PathVariable Long planId) {
//        Optional<SuperAdminPlan> plan = planService.getPlanById(planId);
//
//        if (plan.isPresent()) {
//            List<FeatureDTO> features = planService.getFeaturesByPlanId(planId);
//            Map<String, Object> response = new HashMap<>();
//            response.put("planId", plan.get().getId());
//            response.put("planName", plan.get().getPlanName());
//            response.put("features", features);
//            return response;
//        } else {
//            throw new RuntimeException("Plan not found with ID: " + planId);
//        }
//    }


    @GetMapping("/getCompletePlan/{planId}")
    public PlanDTO getplanData(@PathVariable Long planId) {
        return planService.getplanData(planId);
    }


    @GetMapping("/getPlansBySectionId")
    public SectionWithPlansDTO getSectionWithPlans(@RequestParam Long sectionId) {
        return planService.getPlansBySectionId(sectionId);
    }

    @PostMapping("/assignadditionalAndSystemToPlan")
    public ResponseEntity<AssignSystemAndFeatureResponse> assignSystemsAndFeatures(
            @RequestBody AssignSystemAndFeatureRequest request) {

        AssignSystemAndFeatureResponse response = planService.assignSystemsAndFeaturesToPlan(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateadditionalAndSystemToPlan")
    public ResponseEntity<String> updateSelectedSystemAndFeatureStatus(
            @RequestBody AssignSystemAndFeatureRequest request) {

        planService.updateSelectedSystemAndFeatureStatus(request);
        return ResponseEntity.ok("Selected system and feature status updated successfully.");
    }


}
