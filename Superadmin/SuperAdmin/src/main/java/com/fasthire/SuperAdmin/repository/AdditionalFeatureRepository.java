package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdminAdditionalFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalFeatureRepository extends JpaRepository<SuperAdminAdditionalFeature,Long>
{
}
