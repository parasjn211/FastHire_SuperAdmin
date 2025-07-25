package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.JobPost;

import java.util.List;
import java.util.Optional;

public interface JobPostService {
    JobPost createJobPost(JobPost jobPost);
    List<JobPost> getAllJobPosts();
    Optional<JobPost> getJobPostById(Long id);
    List<JobPost> getByEmployerId(Long employerId);
    List<JobPost> getByCategory(String category);
    List<JobPost> getByLocation(String location);
    List<JobPost> search(String keyword);
}
