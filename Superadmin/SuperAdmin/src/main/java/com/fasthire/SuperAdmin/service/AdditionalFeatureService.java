package com.fasthire.SuperAdmin.service;

import com.fasthire.SuperAdmin.entity.SuperAdminAdditionalFeature;

import java.util.List;

public interface AdditionalFeatureService {
    SuperAdminAdditionalFeature createFeature(SuperAdminAdditionalFeature feature);
    SuperAdminAdditionalFeature getFeatureById(Long id);
    List<SuperAdminAdditionalFeature> getAllFeatures();
    SuperAdminAdditionalFeature updateFeature(Long id, SuperAdminAdditionalFeature updatedFeature);
    void deleteFeature(Long id);

}
