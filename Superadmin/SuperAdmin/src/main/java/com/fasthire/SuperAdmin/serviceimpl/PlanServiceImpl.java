package com.fasthire.SuperAdmin.serviceimpl;
import com.fasthire.SuperAdmin.dto.PlanDTO;
import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.dto.AdditionalFeatureDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanSystem;
import com.fasthire.SuperAdmin.entity.SuperAdminSection;
import com.fasthire.SuperAdmin.repository.PlanRepository;
import com.fasthire.SuperAdmin.repository.SectionRepository;
import com.fasthire.SuperAdmin.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final SectionRepository sectionRepository;

    @Override
    public SuperAdminPlan createPlan(PlanDTO planDTO) {
        SuperAdminSection section = sectionRepository.findById(planDTO.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + planDTO.getSectionId()));

        SuperAdminPlan plan = new SuperAdminPlan();
        plan.setPlanName(planDTO.getPlanName());
        plan.setMrp(planDTO.getMrp());
        plan.setEmployeeCount(planDTO.getEmployeeCount());
        plan.setPerEntry(planDTO.getPerEntry());
        plan.setDescription(planDTO.getDescription());
        plan.setSuperAdminSection(section);

        // Map systems if provided
        if (plan.getPlanSystems() != null) {
            List<SystemDTO> systemDTOS = plan.getPlanSystems().stream()
                    .map(ps -> new SystemDTO(
                            ps.getSystem().getId(),
                            ps.getSystem().getSystemName(),
                            ps.isEnabled()
                    ))
                    .collect(Collectors.toList());
            dto.setSystems(systemDTOS);
        }

        return planRepository.save(plan);
    }

    @Override
    public Optional<SuperAdminPlan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        return planRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SuperAdminPlan updatePlan(Long id, PlanDTO planDTO) {
        SuperAdminPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + id));

        plan.setPlanName(planDTO.getPlanName());
        plan.setMrp(planDTO.getMrp());
        plan.setEmployeeCount(planDTO.getEmployeeCount());
        plan.setPerEntry(planDTO.getPerEntry());
        plan.setDescription(planDTO.getDescription());

        if (planDTO.getSectionId() != null) {
            SuperAdminSection section = sectionRepository.findById(planDTO.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with ID: " + planDTO.getSectionId()));
            plan.setSuperAdminSection(section);
        }

        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    // Helper method
    private PlanDTO convertToDTO(SuperAdminPlan plan) {
        PlanDTO dto = new PlanDTO();
        dto.setId(plan.getId());
        dto.setPlanName(plan.getPlanName());
        dto.setMrp(plan.getMrp());
        dto.setEmployeeCount(plan.getEmployeeCount());
        dto.setPerEntry(plan.getPerEntry());
        dto.setDescription(plan.getDescription());
        dto.setSectionId(plan.getSuperAdminSection().getId());
        dto.setSectionName(plan.getSuperAdminSection().getSectionName());

        if (plan.getPlanSystems() != null) {
            List<SystemDTO> systemDTOS = plan.getPlanSystems().stream()
                    .map(ps -> new SystemDTO(ps.getId(), ps.getSystem()))
                    .collect(Collectors.toList());
            dto.setSystems(systemDTOS);
        }

        return dto;
    }
}
