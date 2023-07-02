package com.elitexplorer.backend.category.controller;

import com.elitexplorer.backend.category.service.CategoryInterface;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    CategoryInterface categoryInterface;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseMessage.success(categoryInterface.getAll().stream().map(DtoConvert::convert).collect(Collectors.toList()));
    }
}
