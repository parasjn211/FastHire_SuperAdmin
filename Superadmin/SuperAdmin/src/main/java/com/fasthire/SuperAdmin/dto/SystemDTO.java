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
public class SystemDTO {
    private Long id;
    private String name;
    private List<String> features;
    private boolean enabled;

    public SystemDTO(Long id, String name, List<String> features) {
        this.id = id;
        this.name = name;
        this.features = features;
    }

    public SystemDTO(Long id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

}