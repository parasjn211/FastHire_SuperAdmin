package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminPlanAdditionalFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean enabled;


    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SuperAdminPlan plan;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private SuperAdminAdditionalFeature feature;

}
