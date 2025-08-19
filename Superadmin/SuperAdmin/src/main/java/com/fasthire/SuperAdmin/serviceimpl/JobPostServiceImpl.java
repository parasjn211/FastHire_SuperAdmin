package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.JobPost;
import com.fasthire.SuperAdmin.repository.JobPostRepository;
import com.fasthire.SuperAdmin.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostServiceImpl implements JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    @Override
    public JobPost createJobPost(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }

    @Override
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @Override
    public Optional<JobPost> getJobPostById(Long id) {
        return jobPostRepository.findById(id);
    }

    @Override
    public List<JobPost> getByEmployerId(Long employerId) {
        return jobPostRepository.findByEmployerId(employerId);
    }

    @Override
    public List<JobPost> getByCategory(String category) {
        return jobPostRepository.findByCategory(category);
    }

    @Override
    public List<JobPost> getByLocation(String location) {
        return jobPostRepository.findByLocation(location);
    }

    @Override
    public List<JobPost> search(String keyword) {
        return jobPostRepository.searchByTitleOrDescription(keyword);
    }
}