package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {

    JobApplication applyToJob(Long userId, Long jobPostId);

    List<JobApplication> getApplicationsByUser(Long userId);

    List<JobApplication> getApplicationsByJobPost(Long jobPostId);

    String updateApplicationStatus(Long applicationId, String status);
}

