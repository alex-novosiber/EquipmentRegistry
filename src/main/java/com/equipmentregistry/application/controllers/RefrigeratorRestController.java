package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.models.Refrigerator;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.services.ComputerService;
import com.equipmentregistry.application.services.RefrigeratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/refrigerator")
public class RefrigeratorRestController {

    private final RefrigeratorService refrigeratorService;

    public RefrigeratorRestController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }


    @GetMapping("/findRefrigeratorByTitle")
    public List<Refrigerator> findRefrigeratorByTitle(@RequestParam Map<String, String> paramsMap){
        return refrigeratorService.findByTitle(paramsMap.get("title"));
    }

    @GetMapping("/findRefrigeratorByTitleAndParameters")
    public StaffEntity findRefrigeratorByTitleAndParameters(@RequestParam Map<String, String> paramsMap){
        return refrigeratorService.findByTitleAndParameters(paramsMap);
    }

    @PostMapping("/saveewrefrigerator")
    public ResponseEntity<?> saveNewRefrigerator(@RequestParam Map<String, String> paramsMap){
        String response = refrigeratorService.addNewRefrigerator(paramsMap);
        if(!response.equals("success")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
