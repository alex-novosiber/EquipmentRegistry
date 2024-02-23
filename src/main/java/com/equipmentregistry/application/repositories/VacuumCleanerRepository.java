package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacuumCleanerRepository extends JpaRepository<VacuumCleaner, Long> {

    List<VacuumCleaner> findAll();

}
