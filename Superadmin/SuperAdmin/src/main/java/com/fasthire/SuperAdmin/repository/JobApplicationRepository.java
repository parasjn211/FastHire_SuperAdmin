package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.JobApplication;
import com.fasthire.SuperAdmin.entity.User;
import com.fasthire.SuperAdmin.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // Get applications by user
    @Query("SELECT ja FROM JobApplication ja WHERE ja.user.id = :userId")
    List<JobApplication> findByUserId(Long userId);

    // Get applications for a specific job
    @Query("SELECT ja FROM JobApplication ja WHERE ja.jobPost.id = :jobPostId")
    List<JobApplication> findByJobPostId(Long jobPostId);

    // Check if a user has already applied to a job
    @Query("SELECT COUNT(ja) > 0 FROM JobApplication ja WHERE ja.user = :user AND ja.jobPost = :jobPost")
    boolean existsByUserAndJobPost(User user, JobPost jobPost);
}

