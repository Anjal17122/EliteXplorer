package com.elitexplorer.backend.pdf1.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

@Controller
public class Pdf1Controller {

    @Autowired
    Pdf1Interface pdf1Interface;



    @PostMapping("/save/pdf1")
    public String savePdf1(@ModelAttribute Pdf1Dto pdf1Dto) throws IOException, ParseException {
        Pdf1 pdf1 = DtoConvert.convert(pdf1Dto);
        if (pdf1Dto.getId()!=0){
            Pdf1 oldPdf = pdf1Interface.getById(pdf1Dto.getId());
            if (pdf1Dto.getFile().isEmpty()){
                pdf1.setFilename(oldPdf.getFilename());
            }else{
                pdf1.setFilename(saveUploadedFile(pdf1Dto.getFile()));
            }
        }else {
            pdf1.setFilename(saveUploadedFile(pdf1Dto.getFile()));
        }
       Pdf1 savedPdf1= pdf1Interface.savePdf1(pdf1);
        return "redirect:/home?id="+savedPdf1.getId();
    }

    @GetMapping("/pdf1/clone/{id}")
    public String clonePdf2(@PathVariable("id") int id){
        pdf1Interface.clonePdf(id);
        return "redirect:/manage/pdf";
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(Constants.imagePath + File.separator+ file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        else {
            System.out.println("File is empty");
            return "File Not Uploaded";
        }
    }

}
