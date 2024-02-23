package com.equipmentregistry.application.controllers;

import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.TvService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // prevent duplicate @ResponseBody annotation on each method
@RequestMapping("/api/v1")
public class TvRestController {

    private final TvService tvService;

    public TvRestController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/findByTitle")
    public List<Tv> findByTitle(@RequestParam(value = "title", required = false) String title){
        return tvService.findByTitle(title);
    }
}
