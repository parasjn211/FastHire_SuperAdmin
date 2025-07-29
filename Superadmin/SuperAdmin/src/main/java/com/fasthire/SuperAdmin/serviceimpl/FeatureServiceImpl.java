package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.entity.SuperAdminFeature;
import com.fasthire.SuperAdmin.repository.FeatureRepository;
import com.fasthire.SuperAdmin.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public SuperAdminFeature createFeature(SuperAdminFeature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public List<SuperAdminFeature> getAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Optional<SuperAdminFeature> getFeatureById(Long id) {
        return featureRepository.findById(id);
    }

    @Override
    public SuperAdminFeature updateFeature(Long id, SuperAdminFeature featureDetails) {
        if (featureRepository.existsById(id)) {
            featureDetails.setId(id);
            return featureRepository.save(featureDetails);
        }
        return null; // Or throw an exception if needed
    }

    @Override
    public boolean deleteFeature(Long id) {
        if (featureRepository.existsById(id)) {
            featureRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
