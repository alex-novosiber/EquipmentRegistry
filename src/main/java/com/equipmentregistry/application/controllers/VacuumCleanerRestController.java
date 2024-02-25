package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.VacuumCleaner;
import com.equipmentregistry.application.services.SmartphoneService;
import com.equipmentregistry.application.services.VacuumCleanerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/vacuumcleaner")
public class VacuumCleanerRestController {

    private final VacuumCleanerService vacuumCleanerService;

    public VacuumCleanerRestController(VacuumCleanerService vacuumCleanerService) {
        this.vacuumCleanerService = vacuumCleanerService;
    }


    @GetMapping("/findVacuumCleanerByTitle")
    public List<VacuumCleaner> findVacuumCleanerByTitle(@RequestParam Map<String, String> paramsMap) {
        return vacuumCleanerService.findByTitle(paramsMap.get("title"));
    }

    @GetMapping("/findVacuumCleanerByTitleAndParameters")
    public StaffEntity findVacuumCleanerByTitleAndParameters(@RequestParam Map<String, String> paramsMap) {
        return vacuumCleanerService.findByTitleAndParameters(paramsMap);
    }

    @PostMapping("/savenewvacuumcleaner")
    public ResponseEntity<?> saveNewVacuumCleaner(@RequestParam Map<String, String> paramsMap){
        String response = vacuumCleanerService.addNewVacuumCleaner(paramsMap);
        if(!response.equals("success")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}