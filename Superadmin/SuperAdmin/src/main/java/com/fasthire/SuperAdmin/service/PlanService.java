package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.AssignSystemAndFeatureRequest;
import com.fasthire.SuperAdmin.dto.AssignSystemAndFeatureResponse;
import com.fasthire.SuperAdmin.dto.PlanDTO;
import com.fasthire.SuperAdmin.dto.SectionWithPlansDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    SuperAdminPlan createPlan(PlanDTO planDTO);
    Optional<SuperAdminPlan> getPlanById(Long planId);
    List<PlanDTO> getAllPlans();
    SuperAdminPlan updatePlan(Long id, PlanDTO planDTO);
    void deletePlan(Long id);
    SuperAdminPlan getPlanByPlanName(String planName);

    SectionWithPlansDTO getPlansBySectionId(Long sectionId);

    //    List<FeatureDTO> getFeaturesByPlanId(Long planId);
    PlanDTO getplanData(Long planId);

    AssignSystemAndFeatureResponse assignSystemsAndFeaturesToPlan(AssignSystemAndFeatureRequest request);
    void updateSelectedSystemAndFeatureStatus(AssignSystemAndFeatureRequest request);

}
