package com.fasthire.SuperAdmin.repository;

import com.fasthire.SuperAdmin.entity.SuperAdminSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SuperAdminSection,Long> {

    @Query("SELECT s FROM SuperAdminSection s WHERE s.sectionName = :sectionName")
    SuperAdminSection findBySectionName(@Param("sectionName") String sectionName);
}
