package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectionName;


    @OneToMany(mappedBy = "superAdminSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperAdminPlan> superAdminPlans;
}
