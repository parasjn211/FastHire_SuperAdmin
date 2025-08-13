package com.fasthire.SuperAdmin.dto;

import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
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
    private List<String> system;
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


    public SystemDTO(Long id, List<String> system) {
        this.id = id;
        this.system = system;
    }
}