package com.fasthire.SuperAdmin.serviceimpl;


import com.fasthire.SuperAdmin.dto.PlanDTO;
import com.fasthire.SuperAdmin.dto.SuperAdminSectionDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminSection;
import com.fasthire.SuperAdmin.repository.SectionRepository;
import com.fasthire.SuperAdmin.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService
{

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public SuperAdminSection createSection(SuperAdminSection superAdminSection) {

        return sectionRepository.save(superAdminSection);
    }

    @Override
    public SuperAdminSection getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + id));
    }

    @Override
    public List<SuperAdminSectionDTO> getAllSections() {
        List<SuperAdminSection> sections = sectionRepository.findAll();

        return sections.stream().map(section -> {
            SuperAdminSectionDTO dto = new SuperAdminSectionDTO();
            dto.setId(section.getId());
            dto.setSectionName(section.getSectionName());

            List<PlanDTO> planDTOs = section.getSuperAdminPlans().stream().map(plan -> {
               PlanDTO planDTO = new PlanDTO();
                planDTO.setId(plan.getId());
                planDTO.setPlanName(plan.getPlanName());
                planDTO.setMrp(plan.getMrp());
                planDTO.setEmployeeCount(plan.getEmployeeCount());
                return planDTO;
            }).collect(Collectors.toList());

            dto.setSuperAdminPlans(planDTOs);
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public SuperAdminSection getSectionByName(String sectionName) {
        return sectionRepository.findBySectionName(sectionName);
    }

    @Override
    public SuperAdminSection updateSection(Long id, SuperAdminSection superAdminSection) {
        SuperAdminSection existingSection = getSectionById(id);
        existingSection.setSectionName(superAdminSection.getSectionName());
        return sectionRepository.save(existingSection);
    }

    @Override
    public void deleteSection(Long id) {
        SuperAdminSection superAdminSection = getSectionById(id);
        sectionRepository.delete(superAdminSection);
    }



}

