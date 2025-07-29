package com.fasthire.SuperAdmin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PlanAdditionalFeatureAssignDTO
{
    private Long planId;
    private Map<Long, Boolean> featureEnableMap;


    public PlanAdditionalFeatureAssignDTO(Long planId, Map<Long, Boolean> featureEnableMap) {
        this.planId = planId;
        this.featureEnableMap = featureEnableMap;
    }

}
