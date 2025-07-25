package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.Plan;
import com.fasthire.SuperAdmin.repository.PlanRepository;
import com.fasthire.SuperAdmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public Optional<Plan> getPlanByName(String name) {
        return planRepository.findByName(name);
    }

    @Override
    public List<Plan> getPlansByMaxPrice(double maxPrice) {
        return planRepository.findPlansByPriceLessThanEqual(maxPrice);
    }
}

