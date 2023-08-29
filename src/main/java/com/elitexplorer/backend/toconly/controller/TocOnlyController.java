package com.elitexplorer.backend.toconly.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toc/only")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TocOnlyController {

    @Autowired
    TocOnlyInterface service;

    @Autowired
    Pdf1TocInterface tocInterface;

    @PostMapping("/save")
    public ResponseEntity saveTocOnly(@RequestBody TocOnlyDto tocOnlyDto){
        TocOnly pdf1Toc = service.save(tocOnlyDto);
        return ResponseMessage.success(DtoConvert.convert(pdf1Toc));
    }

    @GetMapping("/by/pdf1toc/{id}/{page}/{size}")
    public ResponseEntity getByPdf1Toc(@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("size") int size){
        return ResponseMessage.success(service.findByPdf1Toc(id, page,size).map(DtoConvert::convert));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id){
        service.deleteById(id);
        return ResponseMessage.success("Deleted Successfully");
    }



}
