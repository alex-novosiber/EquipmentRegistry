package com.equipmentregistry.application.repositories;

import com.equipmentregistry.application.models.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface TvRepository extends JpaRepository<Tv, Long> {

    List<Tv> findAllByAvailabilityIsTrue();

    List<Tv> findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(String title);

//    Tv findById(Long id);

}
