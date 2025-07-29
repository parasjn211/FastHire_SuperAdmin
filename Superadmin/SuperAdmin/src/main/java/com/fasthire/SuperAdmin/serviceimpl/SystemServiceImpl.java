package com.fasthire.SuperAdmin.serviceimpl;

import com.fasthire.SuperAdmin.dto.SystemDTO;
import com.fasthire.SuperAdmin.entity.SuperAdminFeature;
import com.fasthire.SuperAdmin.entity.SuperAdminSystem;
import com.fasthire.SuperAdmin.repository.FeatureRepository;
import com.fasthire.SuperAdmin.repository.SystemRepository;
import com.fasthire.SuperAdmin.service.SystemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemServiceImpl implements SystemService
{
    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    SystemRepository systemRepository;

    @Override
    @Transactional
    public SuperAdminSystem createSystemWithFeatures(String systemName, List<Long> featureIds) {
        SuperAdminSystem system = new SuperAdminSystem();
        system.setSystemName(systemName);

        List<SuperAdminFeature> features = featureRepository.findAllById(featureIds);
        for (SuperAdminFeature feature : features) {
            feature.setSystem(system);
        }

        system.setFeatures(features);

        return systemRepository.save(system);
    }


    @Override
    public List<SystemDTO> getAllSystems() {
        List<SuperAdminSystem> systems = systemRepository.findAll();

        return systems.stream().map(system -> {
            List<String> featureNames = system.getFeatures()
                    .stream()
                    .map(SuperAdminFeature::getFeatureName)
                    .toList();

            return new SystemDTO(system.getId(), system.getSystemName(), featureNames);
        }).toList();
    }


    @Override
    public Optional<SuperAdminSystem> getSystemById(Long id) {
        return systemRepository.findById(id);
    }

    @Override
    public SuperAdminSystem updateSystem(Long id, SuperAdminSystem systemDetails) {
        if (systemRepository.existsById(id)) {
            systemDetails.setId(id);
            return systemRepository.save(systemDetails);
        }
        return null;
    }

   @Override
    public boolean deleteSystem(Long id) {
        if (systemRepository.existsById(id)) {
            systemRepository.deleteById(id);
            return true;
        }
        return false;
    }
//
//    @Override
//    public Optional<SuperAdminSystem> getSystemNameWithFeatures(String systemName) {
//        return systemRepository.findBySystemNameWithFeatures(systemName);
//    }
}
