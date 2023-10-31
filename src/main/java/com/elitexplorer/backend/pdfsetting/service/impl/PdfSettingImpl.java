package com.elitexplorer.backend.pdfsetting.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdfsetting.model.dto.PdfSettingDto;
import com.elitexplorer.backend.pdfsetting.model.entity.PdfSetting;
import com.elitexplorer.backend.pdfsetting.model.entity.SettingType;
import com.elitexplorer.backend.pdfsetting.repository.PdfSettingRepository;
import com.elitexplorer.backend.pdfsetting.service.PdfSettingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfSettingImpl implements PdfSettingInterface {

    @Autowired
    PdfSettingRepository repository;

    @Override
    public PdfSetting savePdfSetting(PdfSettingDto dto){
        PdfSetting setting = repository.findFirstBySetting(dto.getSetting());
        if (setting!=null) {
            setting.setValue(dto.getValue());
        }else {
            setting = DtoConvert.convert(dto);
        }
        return repository.save(setting);

    }

    @Override
    public PdfSetting findBySetting(SettingType settingType){
        return repository.findFirstBySetting(settingType);
    }
}
