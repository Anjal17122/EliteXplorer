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
//    @PostMapping("/save/pdf2")
//    public String savePdf1(@ModelAttribute Pdf2Dto pdf2Dto) throws IOException, ParseException {
//
//        Pdf2 pdf2 = DtoConvert.convert(pdf2Dto);
//        if (pdf2Dto.getId()!=0){
//            Pdf2 pdfOld = pdf2Interface.getById(pdf2Dto.getId());
//            if (pdf2Dto.getImage1().isEmpty()){
//                pdf2.setImage1(pdfOld.getImage1());
//            }else{
//                pdf2.setImage1(saveUploadedFile(pdf2Dto.getImage1()));
//            }
//            if (pdf2Dto.getImage2().isEmpty()){
//                pdf2.setImage2(pdfOld.getImage2());
//            }else{
//                pdf2.setImage2(saveUploadedFile(pdf2Dto.getImage2()));
//            }
//        }else {
//            pdf2.setImage1(saveUploadedFile(pdf2Dto.getImage1()));
//            pdf2.setImage2(saveUploadedFile(pdf2Dto.getImage2()));
//        }
//        pdf2Interface.savePdf1(pdf2);
//        return "redirect:/";
//    }

    @PostMapping("/save/pdf2")
    public ResponseEntity savePdf2(@RequestBody Pdf2Dto pdf2Dto) {
        Pdf2 pdf2 = DtoConvert.convert(pdf2Dto);
        return ResponseMessage.success(DtoConvert.convertSend(pdf2Interface.savePdf1(pdf2)));
    }

    @GetMapping("/by/id/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        return ResponseMessage.success(DtoConvert.convertSend(pdf2Interface.getById(id)));
    }
    @GetMapping("/pdf2/clone/{id}")
    public String clonePdf2(@PathVariable("id") int id){
            pdf2Interface.clonePdf2(id);
            return "redirect:/";
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
