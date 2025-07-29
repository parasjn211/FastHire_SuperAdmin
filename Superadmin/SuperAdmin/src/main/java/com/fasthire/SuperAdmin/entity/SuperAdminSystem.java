package com.fasthire.SuperAdmin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SystemName;


    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SuperAdminFeature> features;


    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperAdminPlanSystem> planSystems;
}
