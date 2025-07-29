package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.SuperAdminSectionDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminSection;

import java.util.List;

public interface SectionService {
    SuperAdminSection createSection(SuperAdminSection superAdminSection);
    SuperAdminSection getSectionById(Long id);
    List<SuperAdminSectionDTO> getAllSections();
    SuperAdminSection getSectionByName(String sectionName);
    SuperAdminSection updateSection(Long id, SuperAdminSection superAdminSection);
    void deleteSection(Long id);

}
