package com.elitexplorer.backend.pdf1pdf2detail.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.service.Interface.Pdf1Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pdf1/pdf2")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pdf1Pdf2DetailController {

    @Autowired
    Pdf1Pdf2Interface pdf1Pdf2Interface;

    @GetMapping("/day/detail/{id}/{pdf2}")
    public ResponseEntity updateDay(@PathVariable("id") int id, @PathVariable("pdf2") int pdf2Id){
        Pdf1Pdf2Detail pdf1Pdf2Detail = pdf1Pdf2Interface.updateDayDetail(id,pdf2Id);
       return  ResponseMessage.success("Successful");
    }

    @GetMapping("/by/pdf1/{id}")
    public ResponseEntity getByPdf1Id(@PathVariable("id") int id){
        return ResponseMessage.success(pdf1Pdf2Interface.findByPdf1Id(id).stream().map(DtoConvert::convert).collect(Collectors.toList()));
    }
}
