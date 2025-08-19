package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.PlanSystemMapRequest;
import com.fasthire.SuperAdmin.dto.SuperAdminPlanSystemDTO;
import com.fasthire.SuperAdmin.dto.SystemDTO;

import java.util.List;

public interface PlanSystemService {
    List<SuperAdminPlanSystemDTO> getSystemsByPlan(Long planId);
    List<SuperAdminPlanSystemDTO> getPlansBySystem(Long systemId);
    List<SuperAdminPlanSystemDTO> assignSystemsToPlan(PlanSystemMapRequest request);
    SuperAdminPlanSystemDTO updateSystemStatus(Long planId, Long systemId, boolean enabled);
    List<SystemDTO> getSystemsByPlanId(Long planId);

}