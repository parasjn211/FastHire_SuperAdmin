package com.fasthire.SuperAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletePlanDTO {
    private Long planId;
    private String planName;
    private String systemName;
    private List<FeatureDTO> assignedFeatures;
    private List<AdditionalFeatureDTO> additionalFeatures;
}
