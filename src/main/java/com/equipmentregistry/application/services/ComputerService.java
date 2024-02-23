package com.equipmentregistry.application.services;


import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.repositories.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }


    public List<Computer> findAll() {
        return computerRepository.findAll();
    }
}
