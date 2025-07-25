package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    Plan createPlan(Plan plan);
    List<Plan> getAllPlans();
    Optional<Plan> getPlanById(Long id);
    Optional<Plan> getPlanByName(String name);
    List<Plan> getPlansByMaxPrice(double maxPrice);
}

