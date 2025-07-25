package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int durationInDays;
    private double price;

    private String features; // Optional: JSON or comma-separated

    @OneToMany(mappedBy = "plan")
    private List<Employee> employees;
}

