package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findAll();

    List<Computer> findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(String title);

    List<Computer> findAllByAvailabilityIsTrue();

}
