package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    Employer saveEmployer(Employer employer);
    List<Employer> getAllEmployers();
    Optional<Employer> getEmployerById(Long id);
    Optional<Employer> getByEmail(String email);
    List<Employer> searchByCompanyName(String keyword);
    List<Employer> getApprovedEmployers();
}

