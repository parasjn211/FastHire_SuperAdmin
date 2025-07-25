package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.JobPost;
import com.fasthire.SuperAdmin.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @PostMapping("/createJobPost")
    public JobPost createJobPost(@RequestBody JobPost jobPost) {
        return jobPostService.createJobPost(jobPost);
    }

    @GetMapping("/getAllJobPosts")
    public List<JobPost> getAllJobPosts() {
        return jobPostService.getAllJobPosts();
    }

    @GetMapping("/getJobPostById/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable Long id) {
        return jobPostService.getJobPostById(id);
    }

    @GetMapping("/getByEmployerId/{employerId}")
    public List<JobPost> getByEmployerId(@PathVariable Long employerId) {
        return jobPostService.getByEmployerId(employerId);
    }

    @GetMapping("/getByCategory/{category}")
    public List<JobPost> getByCategory(@PathVariable String category) {
        return jobPostService.getByCategory(category);
    }

    @GetMapping("/getByLocation/{location}")
    public List<JobPost> getByLocation(@PathVariable String location) {
        return jobPostService.getByLocation(location);
    }

    @GetMapping("/jobposts/search/{keyword}")
    public List<JobPost> search(@PathVariable String keyword) {
        return jobPostService.search(keyword);
    }
}

