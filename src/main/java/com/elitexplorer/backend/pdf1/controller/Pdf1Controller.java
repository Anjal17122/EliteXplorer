package com.elitexplorer.backend.pdf1.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
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
public class Pdf1Controller {

    @Autowired
    Pdf1Interface pdf1Interface;

    @PostMapping("/save/pdf1")
    public String savePdf1(@ModelAttribute Pdf1Dto pdf1Dto) throws IOException, ParseException {
        System.out.println("Yaaaa" + pdf1Dto.getTitle());
        Pdf1 pdf1 = DtoConvert.convert(pdf1Dto);
        pdf1.setFilename(saveUploadedFile(pdf1Dto.getFile()));
        pdf1Interface.savePdf1(pdf1);
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
