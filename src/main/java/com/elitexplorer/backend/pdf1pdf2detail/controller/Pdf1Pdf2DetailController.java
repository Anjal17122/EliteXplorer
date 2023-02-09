package com.elitexplorer.backend.pdf1pdf2detail.controller;

import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.service.Interface.Pdf1Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf1/pdf2")
public class Pdf1Pdf2DetailController {

    @Autowired
    Pdf1Pdf2Interface pdf1Pdf2Interface;

    @GetMapping("day/detail/{id}/{pdf2}")
    public String updateDay(@PathVariable("id") int id, @PathVariable("pdf2") int pdf2Id){
        Pdf1Pdf2Detail pdf1Pdf2Detail = pdf1Pdf2Interface.updateDayDetail(id,pdf2Id);
        return "redirect:/home?id="+pdf1Pdf2Detail.getPdf1().getId();
    }
}
