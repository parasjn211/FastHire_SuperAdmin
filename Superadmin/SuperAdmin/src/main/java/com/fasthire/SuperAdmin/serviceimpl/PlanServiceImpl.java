package com.fasthire.SuperAdmin.serviceimpl;
import com.fasthire.SuperAdmin.dto.*;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.repository.PlanRepository;
import com.fasthire.SuperAdmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public SuperAdminPlan createPlan(PlanDTO planDTO) {
        SuperAdminPlan plan = new SuperAdminPlan();
        plan.setPlanName(planDTO.getPlanName());
        plan.setDescription(planDTO.getDescription());
        return planRepository.save(plan);
    }

    @Override
    public Optional<SuperAdminPlan> getPlanById(Long planId) {
        return planRepository.findById(planId);
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        return planRepository.findAll().stream().map(plan -> {
            PlanDTO dto = new PlanDTO();
            dto.setId(plan.getId());
            dto.setPlanName(plan.getPlanName());
            dto.setDescription(plan.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SuperAdminPlan updatePlan(Long id, PlanDTO planDTO) {
        SuperAdminPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + id));
        plan.setPlanName(planDTO.getPlanName());
        plan.setDescription(planDTO.getDescription());
        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }


    @Override
    public SectionWithPlansDTO getPlansBySectionId(Long sectionId) {
        // Dummy response or logic stub
        return new SectionWithPlansDTO(); // implement this as needed
    }

    @Override
    public PlanDTO getplanData(Long planId) {
        SuperAdminPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + planId));

        PlanDTO dto = new PlanDTO();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setDescription(plan.getDescription());
        return dto;
    }

    @Override
    public AssignSystemAndFeatureResponse assignSystemsAndFeaturesToPlan(AssignSystemAndFeatureRequest request) {
        // Dummy implementation or delegate to appropriate logic
        return new AssignSystemAndFeatureResponse(); // implement as needed
    }

    @Override
    public void updateSelectedSystemAndFeatureStatus(AssignSystemAndFeatureRequest request) {
        // Implement logic here
    }
}
