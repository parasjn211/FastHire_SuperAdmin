package com.fasthire.SuperAdmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    private int perEntry;
    private Double mrp;
    private String employeeCount;
    private String employerCount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties({"superAdminPlans"})
    private SuperAdminSection superAdminSection;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperAdminPlanSystem> planSystems;

}
