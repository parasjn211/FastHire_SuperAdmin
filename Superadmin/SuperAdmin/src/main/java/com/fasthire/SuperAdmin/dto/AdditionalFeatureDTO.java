package com.fasthire.SuperAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalFeatureDTO
{
    private String name;
    private boolean enabled;
}
