package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.repositories.SmartphoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }


    public List<SmartPhone> findAll() {
        return smartphoneRepository.findAll();
    }
}
