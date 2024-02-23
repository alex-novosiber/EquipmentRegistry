package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefrigeratorRepository  extends JpaRepository<Refrigerator, Long> {

    List<Refrigerator> findAll();

}
