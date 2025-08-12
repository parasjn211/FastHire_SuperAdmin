package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureAssignDTO;
import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminAdditionalFeature;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanAdditionalFeature;
import com.fasthire.SuperAdmin.repository.AdditionalFeatureRepository;
import com.fasthire.SuperAdmin.repository.PlanAdditionalFeatureRepository;
import com.fasthire.SuperAdmin.service.PlanAdditionalFeatureService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanAdditionalFeatureServiceImpl implements PlanAdditionalFeatureService
{

    @Autowired
    private PlanAdditionalFeatureRepository planAdditionalFeatureRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private AdditionalFeatureRepository featureRepository;

    @Override
    @Transactional
    public List<PlanAdditionalFeatureDTO> assignAdditionalFeaturesToPlan(PlanAdditionalFeatureAssignDTO dto) {
        SuperAdminPlan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + dto.getPlanId()));

        List<PlanAdditionalFeatureDTO> assignedDTOs = new ArrayList<>();

        dto.getFeatureEnableMap().forEach((featureId, enabled) -> {
            SuperAdminAdditionalFeature feature = featureRepository.findById(featureId)
                    .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + featureId));

            Optional<SuperAdminPlanAdditionalFeature> existing =
                    planAdditionalFeatureRepository.findByPlanIdAndFeatureId(plan.getId(), featureId);

            SuperAdminPlanAdditionalFeature planFeature;

            if (existing.isPresent()) {
                // Already exists, update the enabled flag
                planFeature = existing.get();
                planFeature.setEnabled(enabled);
            } else {
                // Doesn't exist, create new
                planFeature = new SuperAdminPlanAdditionalFeature();
                planFeature.setPlan(plan);
                planFeature.setFeature(feature);
                planFeature.setEnabled(enabled);
            }

            SuperAdminPlanAdditionalFeature saved = planAdditionalFeatureRepository.save(planFeature);

            // Convert to DTO
            PlanAdditionalFeatureDTO dtoItem = new PlanAdditionalFeatureDTO();
            dtoItem.setPlanId(plan.getId());
            dtoItem.setPlanName(plan.getPlanName());
            dtoItem.setAddFeatureId(feature.getId());
            dtoItem.setAddFeatureName(feature.getName());
            dtoItem.setEnabled(saved.isEnabled());

            assignedDTOs.add(dtoItem);
        });

        return assignedDTOs;
    }



    @Override
    public List<PlanAdditionalFeatureDTO> getFeaturesByPlan(Long planId) {
        return planAdditionalFeatureRepository.findFeaturesByPlanId(planId);
    }

    @Override
    @Transactional
    public void updateFeatureStatus(Long planId, Long featureId, boolean enabled) {
        SuperAdminPlanAdditionalFeature assignment = planAdditionalFeatureRepository
                .findByPlanIdAndFeatureId(planId, featureId)
                .orElseThrow(() -> new RuntimeException("Feature not assigned to plan"));

        assignment.setEnabled(enabled);
        planAdditionalFeatureRepository.save(assignment);
    }




    @Override
    public void deleteFeatureAssignment(Long id) {
        planAdditionalFeatureRepository.deleteById(id);
    }

}
