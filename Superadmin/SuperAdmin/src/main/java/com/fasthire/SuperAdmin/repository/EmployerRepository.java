package com.fasthire.SuperAdmin.repository;
import com.fasthire.SuperAdmin.entity.Employer;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    @Query("SELECT e FROM Employer e WHERE e.email = :email")
    Optional<Employer> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM Employer e WHERE e.isApproved = true")
    List<Employer> findApprovedEmployers();

    @Query("SELECT e FROM Employer e WHERE e.companyName LIKE %:keyword%")
    List<Employer> searchByCompanyName(@Param("keyword") String keyword);
}

