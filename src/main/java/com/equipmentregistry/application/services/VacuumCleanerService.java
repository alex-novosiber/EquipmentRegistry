package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.VacuumCleaner;
import com.equipmentregistry.application.repositories.VacuumCleanerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacuumCleanerService {

    private final VacuumCleanerRepository vacuumCleanerRepository;

    public VacuumCleanerService(VacuumCleanerRepository vacuumCleanerRepository) {
        this.vacuumCleanerRepository = vacuumCleanerRepository;
    }

    public List<VacuumCleaner> findAll() {
        return vacuumCleanerRepository.findAll();
    }

}
