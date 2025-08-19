package com.fasthire.SuperAdmin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String contactPerson;
    private String email;
    private String password;

    private boolean isApproved;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_EMPLOYER;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<JobPost> jobPosts;
}

