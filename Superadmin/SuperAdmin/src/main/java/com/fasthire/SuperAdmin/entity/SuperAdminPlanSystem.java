package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminPlanSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SuperAdminPlan plan;

    @ManyToOne
    @JoinColumn(name = "system_id", nullable = false)
    private SuperAdminSystem system;

    private boolean enabled;
}
