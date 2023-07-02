package com.elitexplorer.backend.category.controller;

import com.elitexplorer.backend.category.service.SubCategoryInterface;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/sub/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubCategoryController {

    @Autowired
    SubCategoryInterface service;


    @GetMapping
    public ResponseEntity getAll(){
        return ResponseMessage.success(service.getAll().stream().map(DtoConvert::convert).collect(Collectors.toList()));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getByCategory(@PathVariable("id") int id){
        return ResponseMessage.success(service.getByCategory(id).stream().map(DtoConvert::convert));
    }
}
