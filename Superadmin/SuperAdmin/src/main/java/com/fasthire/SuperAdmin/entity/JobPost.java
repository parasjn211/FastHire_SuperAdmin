package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String industry;
    private String location;
    private String jobProfile;
    private String jobSkills;

    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}

