package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.JobPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    @Query("SELECT j FROM JobPost j WHERE j.employer.id = :employerId")
    List<JobPost> findByEmployerId(@Param("employerId") Long employerId);

    @Query("SELECT j FROM JobPost j WHERE j.category = :category")
    List<JobPost> findByCategory(@Param("category") String category);

    @Query("SELECT j FROM JobPost j WHERE j.location = :location")
    List<JobPost> findByLocation(@Param("location") String location);

    @Query("SELECT j FROM JobPost j WHERE j.title LIKE %:keyword% OR j.description LIKE %:keyword%")
    List<JobPost> searchByTitleOrDescription(@Param("keyword") String keyword);
}

