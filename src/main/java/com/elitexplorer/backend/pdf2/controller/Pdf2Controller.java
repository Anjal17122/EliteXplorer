package com.elitexplorer.backend.pdf2.controller;


import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2Dto;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        pdf2.setImage1(saveUploadedFile(pdf2Dto.getImage1()));
        pdf2.setImage2(saveUploadedFile(pdf2Dto.getImage2()));
        pdf2Interface.savePdf1(pdf2);
        return "redirect:/?id=0";
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
