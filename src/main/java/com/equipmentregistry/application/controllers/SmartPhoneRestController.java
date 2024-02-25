package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.Refrigerator;
import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.services.RefrigeratorService;
import com.equipmentregistry.application.services.SmartphoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/smartphone")
public class SmartPhoneRestController {

    private final SmartphoneService smartphoneService;

    public SmartPhoneRestController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }


    @GetMapping("/findSmartPhoneByTitle")
    public StaffEntity findSmartPhoneByTitle(@RequestParam Map<String, String> paramsMap) {
        StaffEntity se = new StaffEntity();
        List<SmartPhone> spl = smartphoneService.findByTitle(paramsMap.get("title"));
        se.setEntities(spl);
        return se;
    }

    @GetMapping("/findSmartPhoneByTitleAndParameters")
    public StaffEntity findSmartPhoneByTitleAndParameters(@RequestParam Map<String, String> paramsMap) {
        return smartphoneService.findByTitleAndParameters(paramsMap);
    }

    @PostMapping("/saveewsmartphone")
    public ResponseEntity<?> saveNewSmartphone(@RequestParam Map<String, String> paramsMap){
        String response = smartphoneService.addNewSmartphone(paramsMap);
        if(!response.equals("success")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
