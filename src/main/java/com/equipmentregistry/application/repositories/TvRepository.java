package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvRepository extends JpaRepository<Tv, Long> {

    List<Tv> findAll();
}
