package com.elitexplorer.backend.frontend.home.controller;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.service.Interface.Pdf1Pdf2Interface;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    Pdf1Interface service;


    @Autowired
    Pdf2Interface pdf2Interface;

    @Autowired
    Pdf1Pdf2Interface pdf1Pdf2Interface;

    @GetMapping
    public String homePage(Model model, @RequestParam("id") int id){
        Pdf1 pdf1 = service.getById(id);
        List<Pdf2> pdf2 = pdf2Interface.findAll();
        List<Pdf1Pdf2Detail> pdf1Pdf2Details = new ArrayList<>();
        if (pdf1.getId()!=0) {
            pdf1Pdf2Details = pdf1Pdf2Interface.findAll(pdf1);
        }
        model.addAttribute("pdf1", pdf1);
        model.addAttribute("pdf2",pdf2);
        model.addAttribute("days",pdf1Pdf2Details);
        return "home";
    }


}
