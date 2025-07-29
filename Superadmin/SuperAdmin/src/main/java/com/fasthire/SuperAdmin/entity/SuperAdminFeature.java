package com.fasthire.SuperAdmin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SuperAdminFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String featureName;

    @ManyToOne
    @JoinColumn(name = "system_id")
    @JsonBackReference
    private SuperAdminSystem system;

}
