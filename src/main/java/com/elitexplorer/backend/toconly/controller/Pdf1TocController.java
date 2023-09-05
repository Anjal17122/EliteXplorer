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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pdf1/toc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pdf1TocController {

    @Autowired
    Pdf1TocInterface service;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseMessage.success(service.findAll().stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        return ResponseMessage.success(DtoConvert.convertToDto(service.findById(id)));
    }

    @GetMapping("/search/by/name")
    public ResponseEntity searchByName(@RequestParam("name") String name){
        return ResponseMessage.success(service.searchByName(name).stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/search/by/title")
    public ResponseEntity searchByTitle(@RequestParam("title") String title){
        return ResponseMessage.success(service.searchByTitle(title).stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/search/by/id")
    public ResponseEntity searchById(@RequestParam("id") int id){
        return ResponseMessage.success(service.searchById(id).stream().map(DtoConvert::convertToDto).collect(Collectors.toList()));
    }


    @PostMapping("/save")
    public ResponseEntity savePdf1Toc(@RequestBody Pdf1TocDto pdf1Dto) {
        Pdf1Toc pdf1 = DtoConvert.convert(pdf1Dto);
        Pdf1Toc savedPdf1= service.save(pdf1);
        return ResponseMessage.success(DtoConvert.convertToDto(savedPdf1));
    }

    @GetMapping("/clone/{id}")
    public ResponseEntity clonePdf1Toc(@PathVariable("id") int id){
        Pdf1Toc pdf1Toc = service.clone(id);
        return ResponseMessage.success(DtoConvert.convertToDto(pdf1Toc));
    }

    @GetMapping("/transfer/{id}")
    public ResponseEntity transferPdf1Toc(@PathVariable("id") int id){
        service.transfer(id);
       return ResponseMessage.success(true);
    }



}
