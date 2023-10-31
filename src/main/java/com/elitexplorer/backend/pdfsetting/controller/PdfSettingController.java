package com.elitexplorer.backend.pdfsetting.controller;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.pdfsetting.model.dto.PdfSettingDto;
import com.elitexplorer.backend.pdfsetting.model.entity.SettingType;
import com.elitexplorer.backend.pdfsetting.service.PdfSettingInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf/setting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PdfSettingController {

    @Autowired
    PdfSettingInterface service;

    @PostMapping
    public ResponseEntity saveSetting(@RequestBody PdfSettingDto dto ){
        return ResponseMessage.success(DtoConvert.convert(service.savePdfSetting(dto)));
    }

    @GetMapping
    public ResponseEntity getBySettingType(@RequestParam("settingType")SettingType settingType){
        return ResponseMessage.success(DtoConvert.convert(service.findBySetting(settingType)));
    }

}
