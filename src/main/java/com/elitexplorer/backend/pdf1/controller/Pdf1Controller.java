package com.elitexplorer.backend.pdf1.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/main/pdf1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pdf1Controller {

    @Autowired
    Pdf1Interface pdf1Interface;



    @PostMapping("/save/pdf1")
    public ResponseEntity savePdf1(@RequestBody Pdf1Dto pdf1Dto){
        Pdf1 pdf1 = DtoConvert.convert(pdf1Dto);
        Pdf1 savedPdf1= pdf1Interface.savePdf1(pdf1);
        return ResponseMessage.success(DtoConvert.convertToDto(savedPdf1));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        return ResponseMessage.success(DtoConvert.convertToDto(pdf1Interface.getById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseMessage.success(pdf1Interface.getALl().stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/subcategory")
    public ResponseEntity getBySubCategory(@RequestParam("category") int id){
        return ResponseMessage.success(pdf1Interface.findBySubCategory(id).stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/pdf1/clone/{id}")
    public String clonePdf2(@PathVariable("id") int id){
        pdf1Interface.clonePdf(id);
        return "redirect:/manage/pdf";
    }


}
