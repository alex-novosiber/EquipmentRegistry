package com.equipmentregistry.application.controllers;


import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.TvService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StaffController {

    private final TvService tvService;

    public StaffController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/tv-page")
    public String tvPage(Model model) {
        List<Tv> tvList = tvService.findAllTv();
        model.addAttribute("tvList", tvList);
        return "tv-page";
    }

    @GetMapping("/add-tv")
    public String addTv(Model model) {
        Tv tv = new Tv();
        model.addAttribute("tv", tv);
        return "add-tv";
    }

    @PostMapping("/savenewtv")
    public String saveNewTv(Tv newTv){
        if(tvService.saveNewTv(newTv)) {
            return "tv-page";
        }else {
            return "not-saved";
        }
    }

}
