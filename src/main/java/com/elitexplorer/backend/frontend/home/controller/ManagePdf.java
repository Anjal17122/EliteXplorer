package com.elitexplorer.backend.frontend.home.controller;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.service.Interface.Pdf1Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manage/pdf")
public class ManagePdf {


    @Autowired
    Pdf1Interface pdf1Interface;

    @Autowired
    Pdf1Pdf2Interface detail;

    @GetMapping
    public String managePdf(Model model){
        List<Pdf1> pdf1List = pdf1Interface.getALl().stream().map((a)->addPdf2Uploaded(a)).collect(Collectors.toList());
        model.addAttribute("pdf1",pdf1List);
        return "ManagePdf";
    }

    private Pdf1 addPdf2Uploaded(Pdf1 pdf1){
        List<Pdf1Pdf2Detail> allDetails = detail.checkPdf2Null(pdf1);
        if (allDetails.isEmpty()){
            pdf1.setAllPdf2Uploaded(true);
        }else{
            pdf1.setAllPdf2Uploaded(false);
        }
        return pdf1;
    }


}
