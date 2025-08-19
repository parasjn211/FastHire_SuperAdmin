package com.fasthire.SuperAdmin.serviceimpl;


import com.fasthire.SuperAdmin.dto.PlanSystemMapRequest;
import com.fasthire.SuperAdmin.dto.SuperAdminPlanSystemDTO;
import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanSystem;
import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
import com.fasthire.SuperAdmin.repository.PlanRepository;
import com.fasthire.SuperAdmin.repository.PlanSystemRepository;
import com.fasthire.SuperAdmin.repository.SystemRepository;
import com.fasthire.SuperAdmin.service.PlanSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasthire.SuperAdmin.entity.SuperAdminFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanSystemServiceImpl implements PlanSystemService
{

    @Autowired
    private PlanSystemRepository planSystemRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Override
    public List<SuperAdminPlanSystemDTO> getSystemsByPlan(Long planId) {
        List<SuperAdminPlanSystem> planSystems = planSystemRepository.findByPlanId(planId);
        return planSystems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SuperAdminPlanSystemDTO> getPlansBySystem(Long systemId) {
        List<SuperAdminPlanSystem> planSystems = planSystemRepository.findBySystemId(systemId);
        return planSystems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private SuperAdminPlanSystemDTO convertToDTO(SuperAdminPlanSystem planSystem) {
        return new SuperAdminPlanSystemDTO(
                planSystem.getId(),
                planSystem.getPlan().getId(),
                planSystem.getPlan().getPlanName(),
                planSystem.getSystem().getId(),
                planSystem.getSystem().getSystemName(),
                planSystem.isEnabled(),
                planSystem.getSystem().getFeatures().stream()
                        .map(SuperAdminFeature::getFeatureName)
                        .collect(Collectors.toList())

        );
    }

    @Override
    public List<SuperAdminPlanSystemDTO> assignSystemsToPlan(PlanSystemMapRequest request) {
        SuperAdminPlan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + request.getPlanId()));

        List<SuperAdminPlanSystemDTO> resultList = new ArrayList<>();

        for (Map.Entry<Long, Boolean> entry : request.getSystemEnableMap().entrySet()) {
            Long systemId = entry.getKey();
            boolean enabled = entry.getValue();

            SuperAdminSystem system = systemRepository.findById(systemId)
                    .orElseThrow(() -> new RuntimeException("System not found with ID: " + systemId));

            // Check if mapping already exists
            Optional<SuperAdminPlanSystem> existingOpt = planSystemRepository.findByPlanIdAndSystemId(plan.getId(), systemId);
            SuperAdminPlanSystem planSystem;

            if (existingOpt.isPresent()) {
                planSystem = existingOpt.get();
                planSystem.setEnabled(enabled); // update existing
            } else {
                planSystem = new SuperAdminPlanSystem(); // create new
                planSystem.setPlan(plan);
                planSystem.setSystem(system);
                planSystem.setEnabled(enabled);
            }

            SuperAdminPlanSystem saved = planSystemRepository.save(planSystem);
            resultList.add(toDTO(saved));
        }

        return resultList;
    }

    @Override
    public SuperAdminPlanSystemDTO updateSystemStatus(Long planId, Long systemId, boolean enabled) {
        SuperAdminPlanSystem planSystem = planSystemRepository.findByPlanIdAndSystemId(planId, systemId)
                .orElseThrow(() -> new RuntimeException("System not assigned to this plan"));

        planSystem.setEnabled(enabled);
        return toDTO(planSystemRepository.save(planSystem));
    }

    private SuperAdminPlanSystemDTO toDTO(SuperAdminPlanSystem entity) {
        return new SuperAdminPlanSystemDTO(
                entity.getId(),
                entity.getPlan().getId(),
                entity.getPlan().getPlanName(),
                entity.getSystem().getId(),
                entity.getSystem().getSystemName(),
                entity.isEnabled(),
                entity.getSystem().getFeatures().stream()
                        .map(SuperAdminFeature::getFeatureName)
                        .collect(Collectors.toList())
        );
    }


    @Override
    public List<SystemDTO> getSystemsByPlanId(Long planId) {
        List<SuperAdminPlanSystem> planSystems = planSystemRepository.findByPlanId(planId);
        List<SystemDTO> systemDTOs = new ArrayList<>();

        for (SuperAdminPlanSystem planSystem : planSystems) {
            SuperAdminSystem system = planSystem.getSystem();
            SystemDTO systemDTO = new SystemDTO(
                    system.getId(),
                    system.getSystemName(),
                    planSystem.isEnabled()
            );
            systemDTOs.add(systemDTO);
        }

        return systemDTOs;
    }



}