package com.fasthire.SuperAdmin.controller;

import com.fasthire.SuperAdmin.entity.Employer;
import com.fasthire.SuperAdmin.entity.JobPost;
import com.fasthire.SuperAdmin.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    /**
     * Only EMPLOYERS can create job posts
     */
    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/create")
    public JobPost createJobPost(@RequestBody JobPost jobPost, Authentication authentication) {
        // Get the logged-in employer (assuming EmployerDetails implements UserDetails)
        Employer employer = (Employer) authentication.getPrincipal();

        jobPost.setEmployer(employer);
        jobPost.setPostedDate(LocalDate.now());

        return jobPostService.createJobPost(jobPost);
    }

    /**
     * Anyone (USER, EMPLOYER, ADMIN) can view all job posts
     */
    @GetMapping("/all")
    public List<JobPost> getAllJobPosts() {
        return jobPostService.getAllJobPosts();
    }

    @GetMapping("/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable Long id) {
        return jobPostService.getJobPostById(id);
    }

    /**
     * Employers can view their own posts
     * Admin can also view all by employer if needed
     */
    @PreAuthorize("hasAnyRole('EMPLOYER','SUPERADMIN')")
    @GetMapping("/employer/{employerId}")
    public List<JobPost> getByEmployerId(@PathVariable Long employerId) {
        return jobPostService.getByEmployerId(employerId);
    }

    /**
     * Users, Employers, and Admin can search/filter jobs
     */
    @GetMapping("/category/{category}")
    public List<JobPost> getByCategory(@PathVariable String category) {
        return jobPostService.getByCategory(category);
    }

    @GetMapping("/location/{location}")
    public List<JobPost> getByLocation(@PathVariable String location) {
        return jobPostService.getByLocation(location);
    }

    @GetMapping("/search/{keyword}")
    public List<JobPost> search(@PathVariable String keyword) {
        return jobPostService.search(keyword);
    }
}
