package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdminPlan;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanSystemRepository extends JpaRepository<SuperAdminPlanSystem,Long>
{
    @Query("SELECT ps FROM SuperAdminPlanSystem ps WHERE ps.plan.id = :planId")
    List<SuperAdminPlanSystem> findByPlanId(@Param("planId") Long planId);

    // Get all plans linked to a specific system
    @Query("SELECT ps FROM SuperAdminPlanSystem ps WHERE ps.system.id = :systemId")
    List<SuperAdminPlanSystem> findBySystemId(@Param("systemId") Long systemId);


    @Query("SELECT ps FROM SuperAdminPlanSystem ps WHERE ps.plan.id = :planId AND ps.system.id = :systemId")
    Optional<SuperAdminPlanSystem> findByPlanIdAndSystemId(@Param("planId") Long planId,
                                                           @Param("systemId") Long systemId);

    @Query("SELECT ps FROM SuperAdminPlanSystem ps WHERE ps.plan = :plan")
    List<SuperAdminPlanSystem> findByPlan(@Param("plan") SuperAdminPlan plan);
}
