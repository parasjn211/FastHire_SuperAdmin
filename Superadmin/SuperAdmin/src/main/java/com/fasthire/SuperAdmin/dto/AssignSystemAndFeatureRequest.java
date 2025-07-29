package com.fasthire.SuperAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignSystemAndFeatureRequest
{
    private Long planId;
    private Map<Long, Boolean> systemEnableMap;
    private Map<Long, Boolean> featureEnableMap;

}
