package com.elitexplorer.backend.toconly.controller;

import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toc/only")
public class TocOnlyController {

    @Autowired
    TocOnlyInterface service;

    @Autowired
    Pdf1TocInterface tocInterface;

    @PostMapping("/save")
    public String saveTocOnly(@ModelAttribute TocOnlyDto tocOnlyDto){
        TocOnly pdf1Toc = service.save(tocOnlyDto);
        return "redirect:/pdf1/toc/save/page?id="+tocOnlyDto.getPdf1Toc();
    }


}
