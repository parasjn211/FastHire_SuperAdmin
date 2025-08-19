package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.JobApplication;
import com.fasthire.SuperAdmin.entity.JobPost;
import com.fasthire.SuperAdmin.entity.User;
import com.fasthire.SuperAdmin.repository.JobApplicationRepository;
import com.fasthire.SuperAdmin.repository.JobPostRepository;
import com.fasthire.SuperAdmin.repository.UserRepository;
import com.fasthire.SuperAdmin.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    @Override
    public JobApplication applyToJob(Long userId, Long jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Job post not found"));

        boolean alreadyApplied = jobApplicationRepository.existsByUserAndJobPost(user, jobPost);
        if (alreadyApplied) {
            throw new RuntimeException("User already applied to this job");
        }

        JobApplication jobApplication = JobApplication.builder()
                .user(user)
                .jobPost(jobPost)
                .status("PENDING")
                .build();

        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplication> getApplicationsByUser(Long userId) {
        return jobApplicationRepository.findByUserId(userId);
    }

    @Override
    public List<JobApplication> getApplicationsByJobPost(Long jobPostId) {
        return jobApplicationRepository.findByJobPostId(jobPostId);
    }

    @Override
    public String updateApplicationStatus(Long applicationId, String status) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        jobApplication.setStatus(status.toUpperCase());
        jobApplicationRepository.save(jobApplication);
        return "Application status updated to " + status;
    }
}