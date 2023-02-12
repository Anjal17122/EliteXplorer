package com.elitexplorer.backend.toconly.controller;


import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.dto.Pdf1TocDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

@Controller
@RequestMapping("/pdf1/toc")
public class Pdf1TocController {

    @Autowired
    Pdf1TocInterface service;


    @GetMapping
    public String showMainPage(Model model){
        model.addAttribute("pdf1Toc",service.findAll());
        return "ManageTocOnly";
    }

    @GetMapping("/save/page")
    public String showSavePage(Model model, @RequestParam("id") int id){
        model.addAttribute("pdf1",service.findById(id));
        return "SaveTocOnly";
    }

    @PostMapping("/save")
    public String savePdf1Toc(@ModelAttribute Pdf1TocDto pdf1Dto) throws IOException, ParseException {
        Pdf1Toc pdf1 = DtoConvert.convert(pdf1Dto);
        if (pdf1Dto.getId()!=0){
            Pdf1Toc oldPdf = service.findById(pdf1Dto.getId());
            if (pdf1Dto.getFile().isEmpty()){
                pdf1.setFilename(oldPdf.getFilename());
            }else{
                pdf1.setFilename(saveUploadedFile(pdf1Dto.getFile()));
            }
        }else {
            pdf1.setFilename(saveUploadedFile(pdf1Dto.getFile()));
        }
        Pdf1Toc savedPdf1= service.save(pdf1);
        return "redirect:/pdf1/toc/save/page?id="+savedPdf1.getId();
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
