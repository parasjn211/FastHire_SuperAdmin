package com.fasthire.SuperAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignSystemAndFeatureResponse
{
    private List<SuperAdminPlanSystemDTO> assignedSystems;
    private List<PlanAdditionalFeatureDTO> assignedFeatures;
}
