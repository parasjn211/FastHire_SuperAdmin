package com.fasthire.SuperAdmin.repository;
import com.fasthire.SuperAdmin.entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM Employee e WHERE e.location = :location")
    List<Employee> findByLocation(@Param("location") String location);

    @Query("SELECT e FROM Employee e WHERE e.education = :education")
    List<Employee> findByEducation(@Param("education") String education);
}

