package com.fasthire.SuperAdmin.controller;


import com.fasthire.SuperAdmin.dto.SuperAdminSectionDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminSection;
import com.fasthire.SuperAdmin.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://pjsofttech.in")

public class SectionController
{

    @Autowired
    private SectionService sectionService;

    @PostMapping(value = "/SaveSection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuperAdminSection> createSection(@RequestBody SuperAdminSection superAdminSection) {
        SuperAdminSection createdSection = sectionService.createSection(superAdminSection);
        return ResponseEntity.ok(createdSection);
    }


    @GetMapping("/SectionById/{id}")
    public ResponseEntity<SuperAdminSection> getSectionById(@PathVariable Long id) {
        SuperAdminSection superAdminSection = sectionService.getSectionById(id);
        return ResponseEntity.ok(superAdminSection);
    }


    @GetMapping("/AllSections")
    public ResponseEntity<List<SuperAdminSectionDTO>> getAllSections() {
        List<SuperAdminSectionDTO> sectionList = sectionService.getAllSections();
        return ResponseEntity.ok(sectionList);
    }


    @GetMapping("/searchSectionByName")
    public ResponseEntity<SuperAdminSection> getSectionByName(
            @RequestParam String sectionName) {
        SuperAdminSection superAdminSection = sectionService.getSectionByName(sectionName);
        return ResponseEntity.ok(superAdminSection);
    }

    @PutMapping("/updateSection/{id}")
    public ResponseEntity<SuperAdminSection> updateSection(@PathVariable Long id, @RequestBody SuperAdminSection superAdminSection) {
        SuperAdminSection updatedSection = sectionService.updateSection(id, superAdminSection);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/deleteSection/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }


}

