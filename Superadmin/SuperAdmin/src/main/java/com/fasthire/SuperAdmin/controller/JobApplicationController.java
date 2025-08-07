package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.JobApplication;
import com.fasthire.SuperAdmin.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    // Apply to a job
    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyToJob(
            @RequestParam Long userId,
            @RequestParam Long jobPostId) {
        return ResponseEntity.ok(jobApplicationService.applyToJob(userId, jobPostId));
    }

    // Get applications by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByUser(userId));
    }

    // Get applications by job post (For Employer/Admin)
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJobPost(@PathVariable Long jobPostId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJobPost(jobPostId));
    }

    // Update application status (Employer/Admin)
    @PutMapping("/update-status/{applicationId}")
    public ResponseEntity<String> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(applicationId, status));
    }
}

