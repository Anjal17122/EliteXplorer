package com.elitexplorer.backend.pdf2.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2Dto;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/pdf2")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Pdf2Controller {

    @Autowired
    Pdf2Interface pdf2Interface;

    @PostMapping("/save/pdf2")
    public ResponseEntity savePdf2(@RequestBody Pdf2Dto pdf2Dto) {
        Pdf2 pdf2 = DtoConvert.convert(pdf2Dto);
        return ResponseMessage.success(DtoConvert.convertSend(pdf2Interface.savePdf1(pdf2)));
    }

    @GetMapping("/by/id/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        return ResponseMessage.success(DtoConvert.convertSend(pdf2Interface.getById(id)));
    }


    @GetMapping("/all")
    public ResponseEntity getAll(@RequestParam("page") int page,@RequestParam("size") int size){
        return ResponseMessage.success(pdf2Interface.findAll(page, size).map((a)->DtoConvert.convertSend(a)));
    }

    @GetMapping("/sub/category")
    public ResponseEntity getBySubCategory(@RequestParam("id") int id, @RequestParam("page") int page,  @RequestParam("size") int size){
        return ResponseMessage.success(pdf2Interface.getBySubCategory(id,page,size).map((a)->DtoConvert.convertSend(a)));
    }

    @GetMapping("/category")
    public ResponseEntity getByCategory(@RequestParam("id") int id, @RequestParam("page") int page, @RequestParam("size") int size){
        return ResponseMessage.success(pdf2Interface.getByCategoryId(id,page,size).map((a)->DtoConvert.convertSend(a)));
    }

    @GetMapping("sub/category/title")
    public ResponseEntity getPdf2ByTitle(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("page") int page, @RequestParam("size") int size){
        return ResponseMessage.success(pdf2Interface.getByTitle(id,title,page,size).map((a)->DtoConvert.convertSend(a)));
    }

    @GetMapping("sub/category/by/id")
    public ResponseEntity getPdf2Id(@RequestParam("subCategoryId") int subCategoryId,@RequestParam("id") int id, @RequestParam("page") int page, @RequestParam("size") int size){
        return ResponseMessage.success(pdf2Interface.getById(subCategoryId,id,page,size).map((a)->DtoConvert.convertSend(a)));
    }

    @GetMapping("/search/all")
    public ResponseEntity getAll(){
        return ResponseMessage.success(pdf2Interface.searchAll().stream().map(DtoConvert::convertSend).collect(Collectors.toList()));
    }

    @GetMapping("/search/by/title")
    public ResponseEntity getByTitle(@RequestParam("title") String title){
        return ResponseMessage.success(pdf2Interface.searchByName(title).stream().map(DtoConvert::convertSend).collect(Collectors.toList()));
    }

    @GetMapping("/search/by/id")
    public ResponseEntity searchById(@RequestParam("id") int id){
        return ResponseMessage.success(pdf2Interface.searchById(id).stream().map(DtoConvert::convertSend).collect(Collectors.toList()));
    }


}
