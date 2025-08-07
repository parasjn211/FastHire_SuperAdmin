package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminPlanAdditionalFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanAdditionalFeatureRepository extends JpaRepository<SuperAdminPlanAdditionalFeature, Long>
{

    List<SuperAdminPlanAdditionalFeature> findByPlanId(Long planId);


    @Query("SELECT new com.fasthire.SuperAdmin.dto.PlanAdditionalFeatureDTO(p.id, p.planName, af.id, af.name, pf.enabled) " +
            "FROM SuperAdminPlanAdditionalFeature pf " +
            "JOIN pf.plan p " +
            "JOIN pf.feature af " +
            "WHERE p.id = :planId")
    List<PlanAdditionalFeatureDTO> findFeaturesByPlanId(@Param("planId") Long planId);

    @Query("SELECT p FROM SuperAdminPlanAdditionalFeature p WHERE p.plan.id = :planId AND p.feature.id = :featureId")
    Optional<SuperAdminPlanAdditionalFeature> findByPlanIdAndFeatureId(@Param("planId") Long planId, @Param("featureId") Long featureId);
}
