package com.elitexplorer.backend.toconly.controller;


import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.dto.Pdf1TocDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

@RestController
@RequestMapping("/pdf1/toc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pdf1TocController {

    @Autowired
    Pdf1TocInterface service;

    @Autowired
    TocOnlyInterface tocOnly;



    @GetMapping("/save/page")
    public String showSavePage(Model model, @RequestParam("id") int id){
        model.addAttribute("pdf1",service.findById(id));
        model.addAttribute("toc",tocOnly.findByPdf1Toc(id));
        return "SaveTocOnly";
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        return ResponseMessage.success(DtoConvert.convertToDto(service.findById(id)));
    }


    @PostMapping("/save")
    public ResponseEntity savePdf1Toc(@RequestBody Pdf1TocDto pdf1Dto) {
        Pdf1Toc pdf1 = DtoConvert.convert(pdf1Dto);
        Pdf1Toc savedPdf1= service.save(pdf1);
        return ResponseMessage.success(DtoConvert.convertToDto(savedPdf1));
    }

    @GetMapping("/clone/{id}")
    public String clonePdf1Toc(@PathVariable("id") int id){
        service.clone(id);
        return "redirect:/pdf1/toc";
    }

    @GetMapping("/transfer/{id}")
    public String transferPdf1Toc(@PathVariable("id") int id){
        service.transfer(id);
        return "redirect:/pdf1/toc";
    }



}
