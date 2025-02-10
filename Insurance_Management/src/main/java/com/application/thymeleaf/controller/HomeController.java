package com.application.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "index";
    } 
    @GetMapping("/claimPage")
    public String homePage(Model model) {
    	//model.addAttribute(model);
    	return "claims";
    }
}
