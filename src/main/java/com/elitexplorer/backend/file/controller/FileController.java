package com.elitexplorer.backend.file.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

//,consumes = {"multipart/form-data"}
    @PostMapping(value = "/upload" )
//    public String uploadImage(Model model, @RequestPart("item") Pdf1 pdf1 , @RequestPart("file") MultipartFile file) throws IOException {
            public String uploadImage(@ModelAttribute Pdf1Dto pdf1) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        System.out.println(pdf1.getId());
       saveUploadedFile(pdf1.getFile());
        return "home";
    }

    private void saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            System.out.println(file.getOriginalFilename());
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
        }
        else {
            System.out.println("File is empty");
        }
    }
}
