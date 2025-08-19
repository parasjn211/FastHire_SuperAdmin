package com.fasthire.SuperAdmin.serviceimpl;
import com.fasthire.SuperAdmin.entity.Employer;
import com.fasthire.SuperAdmin.repository.EmployerRepository;
import com.fasthire.SuperAdmin.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Optional<Employer> getEmployerById(Long id) {
        return employerRepository.findById(id);
    }

    @Override
    public Optional<Employer> getByEmail(String email) {
        return employerRepository.findByEmail(email);
    }

    @Override
    public List<Employer> searchByCompanyName(String keyword) {
        return employerRepository.searchByCompanyName(keyword);
    }

    @Override
    public List<Employer> getApprovedEmployers() {
        return employerRepository.findApprovedEmployers();
    }
}