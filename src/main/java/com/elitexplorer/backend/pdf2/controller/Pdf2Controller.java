package com.elitexplorer.backend.pdf2.controller;


import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2Dto;
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
public class Pdf2Controller {

    @Autowired
    Pdf2Interface pdf2Interface;
    @PostMapping("/save/pdf2")
    public String savePdf1(@ModelAttribute Pdf2Dto pdf2Dto) throws IOException, ParseException {

        Pdf2 pdf2 = DtoConvert.convert(pdf2Dto);
        if (pdf2Dto.getId()!=0){
            Pdf2 pdfOld = pdf2Interface.getById(pdf2Dto.getId());
            if (pdf2Dto.getImage1().isEmpty()){
                pdf2.setImage1(pdfOld.getImage1());
            }else{
                pdf2.setImage1(saveUploadedFile(pdf2Dto.getImage1()));
            }
            if (pdf2Dto.getImage2().isEmpty()){
                pdf2.setImage2(pdfOld.getImage2());
            }else{
                pdf2.setImage2(saveUploadedFile(pdf2Dto.getImage2()));
            }
        }else {
            pdf2.setImage1(saveUploadedFile(pdf2Dto.getImage1()));
            pdf2.setImage2(saveUploadedFile(pdf2Dto.getImage2()));
        }
        pdf2Interface.savePdf1(pdf2);
        return "redirect:/";
    }

    @GetMapping("/pdf2/clone/{id}")
    public String clonePdf2(@PathVariable("id") int id){
            pdf2Interface.clonePdf2(id);
            return "redirect:/";
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            File dir = new File(Constants.imagePath);
            if (!dir.exists()) {
//                System.out.println("realPath => " + uploadTempPath);
                dir.mkdirs();
            }
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
