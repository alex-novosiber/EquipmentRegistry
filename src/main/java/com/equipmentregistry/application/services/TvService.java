package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.repositories.TvRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvService {

    private final TvRepository tvRepository;

    public TvService(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }


    public List<Tv> findAllTv() {
        return tvRepository.findAll();
    }
    public boolean saveNewTv(Tv newTv) {
        tvRepository.save(newTv);
        return true;
    }

}
