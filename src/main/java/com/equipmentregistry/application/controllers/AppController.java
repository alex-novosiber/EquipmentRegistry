package com.equipmentregistry.application.controllers;



import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class AppController {


    @GetMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    @GetMapping("/404")
    public String access404() {
        return "error/404";
    }


    @GetMapping("/500")
    public String access500() {
        return "error/500";
    }


    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/index")
    public String viewLoginPage() {
        return "index";
    }




}
