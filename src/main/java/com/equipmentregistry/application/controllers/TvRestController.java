package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.TvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController // prevent duplicate @ResponseBody annotation on each method
@RequestMapping("/api/v1/tv")
public class TvRestController {

    private final TvService tvService;

    public TvRestController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/findTvByTitle")
    public List<Tv> findTvByTitle(@RequestParam Map<String, String> paramsMap){
        return tvService.findByTitle(paramsMap.get("title"));
    }

    @GetMapping("/findTvByTitleAndParameters")
    public StaffEntity findTvByTitleAndParameters(@RequestParam Map<String, String> paramsMap){
        return tvService.findByTitleAndParameters(paramsMap);
    }

    @PostMapping("/savenewtv")
    public ResponseEntity<?> saveNewTv(@RequestParam Map<String, String> paramsMap){
        String response = tvService.addNewTv(paramsMap);
        if(!response.equals("success")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
