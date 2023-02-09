package com.elitexplorer.backend.frontend.home.controller;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage/pdf")
public class ManagePdf {


    @Autowired
    Pdf1Interface pdf1Interface;

    @GetMapping
    public String managePdf(Model model){
        List<Pdf1> pdf1List = pdf1Interface.getALl();
        model.addAttribute("pdf1",pdf1List);
        return "ManagePdf";
    }
}
