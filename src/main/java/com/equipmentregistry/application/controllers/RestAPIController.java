package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.StaffEntityService;
import com.equipmentregistry.application.services.TvService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController // prevent duplicate @ResponseBody annotation on each method
@RequestMapping("/api/v1")
public class RestAPIController {

    private final StaffEntityService entityService;
    private final TvService tvService;

    public RestAPIController(StaffEntityService entityService, TvService tvService) {
        this.entityService = entityService;
        this.tvService = tvService;
    }

    @GetMapping("/registry")
    public StaffEntity registry(@RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "countryOfManufacturer", required = false) String countryOfManufacturer,
                                @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                @RequestParam(value = "onlineOrderingPossibility", required = false) String onlineOrderingPossibility,
                                @RequestParam(value = "installmentPlanPossibility", required = false) String installmentPlanPossibility) {


        return entityService.getEntities(title, countryOfManufacturer, manufacturer, onlineOrderingPossibility, installmentPlanPossibility);
    }


    @GetMapping("/tvRegistry")
    public StaffEntity advancedRegistry(@RequestParam Map <String, String> paramsMap) {
        return entityService.getEntitiesByParamsMap(paramsMap);
    }

    @GetMapping("/tvRegistry2")
    public List<Tv> advancedRegistry2(@RequestParam Map <String, String> paramsMap) {
        return tvService.getTvEntitiesByParamsMap(paramsMap);
    }


}
