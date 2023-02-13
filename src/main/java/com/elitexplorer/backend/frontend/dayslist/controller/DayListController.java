package com.elitexplorer.backend.frontend.dayslist.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("download/images")
public class DayListController {

    @GetMapping
    public String getList(){
        File source = new File(Constants.imageCopySource);
        File dest = new File(Constants.imageCopyDest);
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ImageCopy";
    }

}
