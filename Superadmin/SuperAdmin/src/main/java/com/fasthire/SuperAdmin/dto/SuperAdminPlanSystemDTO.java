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
public class SuperAdminPlanSystemDTO {
    private Long id;
    private Long planId;
    private String planName;
    private Long systemId;
    private String systemName;
    private boolean enabled;
    private List<String> features;
}