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
public class SectionWithPlansDTO {
    private Long id;
    private String name;
    private List<PlanDTO> plans;
}