package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.ComputerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/computer")
public class ComputerRestController {

    private final ComputerService computerService;

    public ComputerRestController(ComputerService computerService) {
        this.computerService = computerService;
    }


    @GetMapping("/findPcByTitle")
    public List<Computer> findPcByTitle(@RequestParam Map<String, String> paramsMap){
        return computerService.findByTitle(paramsMap.get("title"));
    }

    @GetMapping("/findPcByTitleAndParameters")
    public StaffEntity findPcByTitleAndParameters(@RequestParam Map<String, String> paramsMap){
        return computerService.findByTitleAndParameters(paramsMap);
    }


    @PostMapping("/savenewcomputer")
    public ResponseEntity<?> saveNewComputer(@RequestParam Map<String, String> paramsMap){
        String response = computerService.addNewComputer(paramsMap);
        if(!response.equals("success")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
