package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.dto.*;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanSystem;
import com.fasthire.SuperAdmin.entity.SuperAdminSection;
import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
import com.fasthire.SuperAdmin.repository.*;
import com.fasthire.SuperAdmin.service.PlanAdditionalFeatureService;
import com.fasthire.SuperAdmin.service.PlanService;
import com.fasthire.SuperAdmin.service.PlanSystemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private PlanAdditionalFeatureService planAdditionalFeatureService;

    @Autowired
    private PlanSystemService planSystemService;

    @Autowired
    private PlanSystemRepository planSystemRepository;

    @Autowired
    private PlanAdditionalFeatureRepository planAdditionalFeatureRepository;

    @Override
    public SuperAdminPlan createPlan(PlanDTO planDTO) {
        SuperAdminPlan superAdminPlan = new SuperAdminPlan();
        superAdminPlan.setPlanName(planDTO.getPlanName());
        superAdminPlan.setMrp(planDTO.getMrp());
        superAdminPlan.setEmployeeCount(planDTO.getEmployeeCount());
        superAdminPlan.setPerEntry(planDTO.getPerEntry());
//        superAdminPlan.setBranchLimit(planDTO.getBranchLimit());

        SuperAdminSection superAdminSection = sectionRepository.findById(planDTO.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section with ID " + planDTO.getSectionId() + " not found"));
        superAdminPlan.setSuperAdminSection(superAdminSection);

        return planRepository.save(superAdminPlan);
    }

    @Override
    public Optional<SuperAdminPlan> getPlanById(Long id) {
        return planRepository.findById(id);
    }


    @Override
    public List<PlanDTO> getAllPlans() {
        List<SuperAdminPlan> plans = planRepository.findAll();

        return plans.stream().map(plan ->
                new PlanDTO(
                        plan.getId(),
                        plan.getPlanName(),
                        plan.getMrp(),
                        plan.getEmployeeCount(),
                        plan.getStudentCount(),
                        plan.getSuperAdminSection().getId(),
                        plan.getSuperAdminSection().getSectionName(),
                        plan.getPerEntry(),
                        plan.getBranchLimit(),
                        plan.getPlanSystems().stream().map(planSystem ->
                                new SystemDTO(
                                        planSystem.getSystem().getId(),
                                        planSystem.getSystem().getSystemName(),
                                        planSystem.getSystem().getFeatures().stream()
                                                .map(f -> f.getFeatureName())
                                                .collect(Collectors.toList()),
                                        planSystem.isEnabled()
                                )
                        ).collect(Collectors.toList()),
                        plan.getAdditionalFeatures().stream().map(feature ->
                                new AdditionalFeatureDTO(
                                        feature.getFeature().getName(),
                                        feature.isEnabled()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }


    @Override
    public SuperAdminPlan updatePlan(Long id, PlanDTO planDTO) {
        SuperAdminPlan existingPlan = getPlanById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));

        existingPlan.setPlanName(planDTO.getPlanName());
        existingPlan.setMrp(planDTO.getMrp());
        existingPlan.setPerEntry(planDTO.getPerEntry());
        existingPlan.setEmployeeCount(planDTO.getEmployeeCount());
        existingPlan.setStudentCount(planDTO.getStudentCount());

        return planRepository.save(existingPlan);
    }

    @Override
    public void deletePlan(Long id) {
        SuperAdminPlan existingPlan = getPlanById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
        planRepository.delete(existingPlan);
    }

    @Override
    public SuperAdminPlan getPlanByPlanName(String planName) {
        return planRepository.findByPlanName(planName);
    }


//    @Override
//    public List<FeatureDTO> getFeaturesByPlanId(Long planId) {
//        List<SuperAdminPlanAdditionalFeature> planFeatures = planAdditionalFeatureRepository.findByPlanId(planId);
//
//        return planFeatures.stream()
//                .map(pf -> new FeatureDTO(
//                        pf.getSuperAdminFeature().getId(),
//                        pf.getSuperAdminFeature().getFeatureName(),
//                        pf.isEnabled()
//                ))
//                .collect(Collectors.toList());
//    }

    @Override
    public AssignSystemAndFeatureResponse assignSystemsAndFeaturesToPlan(AssignSystemAndFeatureRequest request) {
        List<SuperAdminPlanSystemDTO> systems = planSystemService.assignSystemsToPlan(
                new PlanSystemMapRequest(request.getPlanId(), request.getSystemEnableMap())
        );

        List<PlanAdditionalFeatureDTO> features = planAdditionalFeatureService.assignAdditionalFeaturesToPlan(
                new PlanAdditionalFeatureAssignDTO(request.getPlanId(), request.getFeatureEnableMap())
        );

        return new AssignSystemAndFeatureResponse(systems, features);
    }

    @Transactional
    @Override
    public void updateSelectedSystemAndFeatureStatus(AssignSystemAndFeatureRequest request) {
        Long planId = request.getPlanId();

        if (request.getSystemEnableMap() != null) {
            request.getSystemEnableMap().forEach((systemId, enabled) -> {
                planSystemRepository.findByPlanIdAndSystemId(planId, systemId).ifPresent(planSystem -> {
                    planSystem.setEnabled(enabled);
                    planSystemRepository.save(planSystem);
                });
            });
        }

        if (request.getFeatureEnableMap() != null) {
            request.getFeatureEnableMap().forEach((featureId, enabled) -> {
                planAdditionalFeatureRepository.findByPlanIdAndFeatureId(planId, featureId).ifPresent(planFeature -> {
                    planFeature.setEnabled(enabled);
                    planAdditionalFeatureRepository.save(planFeature);
                });
            });
        }
    }

    @Override
    public PlanDTO getplanData(Long planId) {
        SuperAdminPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + planId));

        return new PlanDTO(
                plan.getId(),
                plan.getPlanName(),
                plan.getMrp(),
                plan.getEmployeeCount(),
                plan.getSuperAdminSection().getId(),
                plan.getSuperAdminSection().getSectionName(),
                plan.getPerEntry(),
                plan.getPlanSystems().stream().map(planSystem ->
                        new SystemDTO(
                                planSystem.getSystem().getId(),
                                planSystem.getSystem().getSystemName(),
                                planSystem.getSystem().getFeatures().stream().map(f -> f.getFeatureName()).collect(Collectors.toList()),
                                planSystem.isEnabled()
                        )
                ).collect(Collectors.toList()),
                plan.getAdditionalFeatures().stream().map(feature ->
                        new AdditionalFeatureDTO(
                                feature.getFeature().getName(),
                                feature.isEnabled()
                        )
                ).collect(Collectors.toList())
        );
    }


    @Override
    public SectionWithPlansDTO getPlansBySectionId(Long sectionId) {
        SuperAdminSection section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + sectionId));

        List<PlanDTO> planDTOs = new ArrayList<>();

        for (SuperAdminPlan plan : section.getSuperAdminPlans()) {

            // Map systems
            List<SystemDTO> systems = new ArrayList<>();
            for (SuperAdminPlanSystem planSystem : plan.getPlanSystems()) {
                SuperAdminSystem systemEntity = planSystem.getSystem();

                List<String> featureNames = systemEntity.getFeatures()
                        .stream()
                        .map(SuperAdminFeature::getFeatureName)
                        .toList();

                SystemDTO systemDTO = new SystemDTO();
                systemDTO.setId(systemEntity.getId());
                systemDTO.setName(systemEntity.getSystemName());
                systemDTO.setEnabled(planSystem.isEnabled());
                systemDTO.setFeatures(featureNames);

                systems.add(systemDTO);
            }

            // Map additional features from join entity
            List<AdditionalFeatureDTO> additionalFeatures = plan.getAdditionalFeatures()
                    .stream()
                    .map(paf -> {
                        AdditionalFeatureDTO dto = new AdditionalFeatureDTO();
                        dto.setName(paf.getFeature().getName()); // Get feature name
                        dto.setEnabled(paf.isEnabled());         // Get enabled flag
                        return dto;
                    })
                    .toList();

            // Create PlanDTO
            PlanDTO planDTO = new PlanDTO(
                    plan.getId(),
                    plan.getPlanName(),
                    plan.getMrp(),
                    plan.getEmployeeCount(),
                    plan.getStudentCount(),
                    section.getId(),
                    section.getSectionName(),
                    plan.getPerEntry(),
                    plan.getBranchLimit(),
                    systems,
                    additionalFeatures
            );

            planDTOs.add(planDTO);
        }

        // Final response
        SectionWithPlansDTO response = new SectionWithPlansDTO();
        response.setId(section.getId());
        response.setName(section.getSectionName());
        response.setPlans(planDTOs);

        return response;
    }




}
