package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.SuperAdminFeature;

import java.util.List;
import java.util.Optional;

public interface FeatureService {

    SuperAdminFeature createFeature(SuperAdminFeature feature);
    List<SuperAdminFeature> getAllFeatures();
    Optional<SuperAdminFeature> getFeatureById(Long id);
    SuperAdminFeature updateFeature(Long id, SuperAdminFeature featureDetails);
    boolean deleteFeature(Long id);

}
