package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<SuperAdminSystem,Long> {
}
