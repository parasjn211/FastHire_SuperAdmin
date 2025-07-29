package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<SuperAdminPlan,Long> {

    @Query("SELECT s FROM SuperAdminPlan s WHERE s.planName = :planName")
    SuperAdminPlan findByPlanName(@Param("planName") String planName);


    @Query("SELECT s FROM SuperAdminPlan s WHERE s.superAdminSection.id = :sectionId")
    List<SuperAdminPlan> findPlansBySectionId(@Param("sectionId") Long sectionId);


    @Query("SELECT s FROM SuperAdminPlan s JOIN FETCH s.superAdminSection")
    List<SuperAdminPlan> findAllWithSection();


}
