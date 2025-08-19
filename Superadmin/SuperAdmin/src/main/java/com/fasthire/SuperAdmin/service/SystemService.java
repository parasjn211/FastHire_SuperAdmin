package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminSystem;

import java.util.List;
import java.util.Optional;

public interface SystemService {
    SuperAdminSystem createSystemWithFeatures(String systemName, List<Long> featureIds);
    List<SystemDTO> getAllSystems();
    Optional<SuperAdminSystem> getSystemById(Long id);
    SuperAdminSystem updateSystem(Long id, SuperAdminSystem systemDetails);
    boolean deleteSystem(Long id);

}