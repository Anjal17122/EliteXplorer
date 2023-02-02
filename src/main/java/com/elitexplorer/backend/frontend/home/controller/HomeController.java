package com.elitexplorer.backend.frontend.home.controller;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    Pdf1Interface service;

    @GetMapping("/")
    public String homePage(Model model, @RequestParam("id") int id){
        Pdf1 pdf1 = service.getById(id);
        model.addAttribute("pdf1", pdf1);
        return "home";
    }

    @PostMapping
    public String saveFrontPage(@ModelAttribute("pdf1") Pdf1 pdf1){
        return "saved";
    }
}
