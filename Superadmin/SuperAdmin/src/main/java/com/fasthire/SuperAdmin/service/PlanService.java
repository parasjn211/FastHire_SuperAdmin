package com.fasthire.SuperAdmin.service;
import com.fasthire.SuperAdmin.dto.PlanDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;

import java.util.List;
import java.util.Optional;

public interface PlanService {

    SuperAdminPlan createPlan(PlanDTO planDTO);

    Optional<SuperAdminPlan> getPlanById(Long id);

    List<PlanDTO> getAllPlans();

    SuperAdminPlan updatePlan(Long id, PlanDTO planDTO);

    void deletePlan(Long id);
}