package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.*;

import com.equipmentregistry.application.repositories.TvRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Service
public class StaffEntityService {




    private final TvService tvService;

    private final VacuumCleanerService vacuumCleanerService;
    private final RefrigeratorService refrigeratorService;
    private final SmartphoneService smartphoneService;
    private final ComputerService computerService;

    public StaffEntityService(TvService tvService, VacuumCleanerService vacuumCleanerService,
                              RefrigeratorService refrigeratorService, SmartphoneService smartphoneService,
                              ComputerService computerService) {
        this.tvService = tvService;
        this.vacuumCleanerService = vacuumCleanerService;
        this.refrigeratorService = refrigeratorService;
        this.smartphoneService = smartphoneService;
        this.computerService = computerService;
    }


    public StaffEntity getEntity() {
        StaffEntity e = new StaffEntity();
        List<Tv> tvList = tvService.findAllTv();
        List<VacuumCleaner> vacuumCleanerList = vacuumCleanerService.findAll();
        List<Refrigerator> refrigeratorsList = refrigeratorService.findAll();
        List<SmartPhone> smartPhoneList = smartphoneService.findAll();
        List<Computer> computerList = computerService.findAll();
        List<StaffEntity> entities = new ArrayList<>();
        entities.addAll(tvList);
        entities.addAll(vacuumCleanerList);
        entities.addAll(refrigeratorsList);
        entities.addAll(smartPhoneList);
        entities.addAll(computerList);
        e.setEntities(entities);

        return e;
    }

    public StaffEntity getEntities(String title, String countryOfManufacturer, String manufacturer,
                                   String onlineOrderingPossibility, String installmentPlanPossibility) {

        if (null != title && !title.isEmpty()) {
            // findByTitle
        } else {
            // find WITHOUT title
        }
        return new StaffEntity();


    }


    public StaffEntity getEntitiesByParamsMap(Map<String, String> paramsMap) {
        System.out.println(paramsMap.toString());
        if (paramsMap.containsKey("title")) {
            System.out.println("containsKey(\"title\")");
        } else if (paramsMap.containsKey("color")) {
            System.out.println("containsKey(\"color\")");
        } else {
            System.out.println("Unexpected value: " + paramsMap);
        }

        return new StaffEntity();
    }




}
