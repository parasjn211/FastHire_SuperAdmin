package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureAssignDTO;
import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureDTO;

import java.util.List;

public interface PlanAdditionalFeatureService {
    List<PlanAdditionalFeatureDTO> assignAdditionalFeaturesToPlan(PlanAdditionalFeatureAssignDTO dto);
    List<PlanAdditionalFeatureDTO> getFeaturesByPlan(Long planId);
    void updateFeatureStatus(Long planId, Long featureId, boolean enabled);
    void deleteFeatureAssignment(Long id);

}
