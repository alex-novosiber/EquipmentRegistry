package com.equipmentregistry.application.services;


import com.equipmentregistry.application.models.Refrigerator;
import com.equipmentregistry.application.repositories.RefrigeratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;

    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
    }


    public List<Refrigerator> findAll() {
        return refrigeratorRepository.findAll();
    }
}
