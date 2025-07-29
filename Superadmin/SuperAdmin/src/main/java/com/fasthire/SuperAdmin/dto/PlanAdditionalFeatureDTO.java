package com.fasthire.SuperAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanAdditionalFeatureDTO
{
    private Long planId;
    private String planName;
    private Long addFeatureId;
    private String addFeatureName;
    private boolean enabled;


}
