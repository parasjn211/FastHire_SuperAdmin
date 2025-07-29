package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.SuperAdminAdditionalFeature;
import com.fasthire.SuperAdmin.repository.AdditionalFeatureRepository;
import com.fasthire.SuperAdmin.service.AdditionalFeatureService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditonalFeatureServiceImpl implements AdditionalFeatureService
{

    @Autowired
    AdditionalFeatureRepository additionalFeatureRepository;

    @Override
    public SuperAdminAdditionalFeature createFeature(SuperAdminAdditionalFeature feature) {
        return additionalFeatureRepository.save(feature);
    }


    @Override
    public SuperAdminAdditionalFeature getFeatureById(Long id) {
        return additionalFeatureRepository.findById(id).orElseThrow(() -> new RuntimeException("Feature not found with ID: " + id));
    }

    @Override
    public List<SuperAdminAdditionalFeature> getAllFeatures() {
        return additionalFeatureRepository.findAll();
    }

    @Override
    @Transactional
    public SuperAdminAdditionalFeature updateFeature(Long id, SuperAdminAdditionalFeature updatedFeature) {
        Optional<SuperAdminAdditionalFeature> optionalFeature = additionalFeatureRepository.findById(id);
        if (optionalFeature.isEmpty()) {
            throw new RuntimeException("Feature not found with ID: " + id);
        }

        SuperAdminAdditionalFeature existingFeature = optionalFeature.get();
        existingFeature.setName(updatedFeature.getName());

        return additionalFeatureRepository.save(existingFeature);
    }

   @Override
    public void deleteFeature(Long id) {
        if (!additionalFeatureRepository.existsById(id)) {
            throw new RuntimeException("Feature not found with ID: " + id);
        }
       additionalFeatureRepository.deleteById(id);
    }
}
