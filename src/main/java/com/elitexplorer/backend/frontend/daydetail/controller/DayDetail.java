package com.elitexplorer.backend.frontend.daydetail.controller;


import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DayDetail {
    @Autowired
    Pdf2Interface service;

    @GetMapping("/day/detail")
    public String dayDetail(Model model, @RequestParam("id") int id){
        Pdf2 pdf1 = service.getById(id);
        model.addAttribute("pdf2", pdf1);
        return "tripdetail";
    }

}
