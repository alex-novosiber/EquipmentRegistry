package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TvRepository extends JpaRepository<Tv, Long> {

    List<Tv> findAll();

    List<Tv> findAllByTitleContainsIgnoreCase(String title);

//    Tv findById(Long id);

}
