package com.equipmentregistry.application.controllers;


import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.services.TvService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value="/getbyid/{id}") //   /getbyid/1
    @ResponseBody
    public Optional<Tv> getEntityById(@PathVariable("id") Long id) {
        System.out.println("getEntityById [" + id + "]");
        return tvService.findById(id);
    }

    @GetMapping("/registry")
    public String registry(Model model) {
        List<Tv> tvList = tvService.findAllTv();
        model.addAttribute("tvList", tvList);
        return "registry";
    }

}
