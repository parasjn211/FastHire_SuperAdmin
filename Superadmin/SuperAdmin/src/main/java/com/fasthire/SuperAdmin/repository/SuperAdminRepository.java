package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByEmailAddress(String emailAddress);

}
