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
public class PlanDTO {
    private Long id;
    private String planName;
    private Double mrp;
    private String employeeCount;
    private Long sectionId;
    private String sectionName;
    private int perEntry;
    private List<SystemDTO> systems;
    private List<AdditionalFeatureDTO> additionalFeatures;
    private String description;
}