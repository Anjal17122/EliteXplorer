package com.elitexplorer.backend.pricingtemplate.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateDto;
import com.elitexplorer.backend.pricingtemplate.service.Interface.PricingTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing/template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PricingTemplateController {

    @Autowired
    PricingTemplateInterface service;

    @PostMapping
    public ResponseEntity savePricingDetail(@RequestBody PricingTemplateDto dto){
        return ResponseMessage.success(DtoConvert.convert(service.saveDetail(dto)));
    }

    @GetMapping
    public ResponseEntity getByPdf1Pdf2(@RequestParam("pdf1pdf2") int id){
        return ResponseMessage.success(DtoConvert.convert(service.getByPdf1Pdf2(id)));
    }

    @GetMapping("/pdf1")
    public ResponseEntity getAllDetailByPdf1Id(@RequestParam("id") int id){
        return ResponseMessage.success(service.getDetailByPdf1Id(id));
    }




}
