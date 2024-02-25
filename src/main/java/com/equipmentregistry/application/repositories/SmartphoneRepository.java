package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Refrigerator;
import com.equipmentregistry.application.models.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, Long> {

    List<SmartPhone> findAllByAvailabilityIsTrue();

    List<SmartPhone> findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(String title);

}
