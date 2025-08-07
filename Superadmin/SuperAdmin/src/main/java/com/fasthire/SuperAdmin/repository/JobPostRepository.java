package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByEmployerId(Long employerId);

    List<JobPost> findByCategory(String category);

    List<JobPost> findByLocation(String location);

    @Query("SELECT j FROM JobPost j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobPost> searchByTitleOrDescription(String keyword);
}
