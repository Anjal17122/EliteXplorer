package com.elitexplorer.backend.frontend.home.controller;

import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPage {

    @Autowired
    Pdf2Interface service;

    @GetMapping
    public String openLandingPage(Model model){
        model.addAttribute("pdf2",service.findAll());
        return "LandingPage";
    }
}
