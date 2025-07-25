package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.Plan;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p WHERE p.name = :name")
    Optional<Plan> findByName(@Param("name") String name);

    @Query("SELECT p FROM Plan p WHERE p.price <= :maxPrice")
    List<Plan> findPlansByPriceLessThanEqual(@Param("maxPrice") double maxPrice);
}

