package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.JobApplication;
import com.fasthire.SuperAdmin.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    // Only USER can apply
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyToJob(
            @RequestParam Long userId,
            @RequestParam Long jobPostId) {
        return ResponseEntity.ok(jobApplicationService.applyToJob(userId, jobPostId));
    }

    // Only USER can view their own applications
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByUser(userId));
    }

    // EMPLOYER or ADMIN can view applications for a job
    @PreAuthorize("hasAnyRole('EMPLOYER','SUPERADMIN')")
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJobPost(@PathVariable Long jobPostId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJobPost(jobPostId));
    }

    // Only EMPLOYER or ADMIN can update application status
    @PreAuthorize("hasAnyRole('EMPLOYER','SUPERADMIN')")
    @PutMapping("/update-status/{applicationId}")
    public ResponseEntity<String> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(applicationId, status));
    }
}
