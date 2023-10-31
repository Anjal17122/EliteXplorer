package com.elitexplorer.backend.pdfsetting.service;

import com.elitexplorer.backend.pdfsetting.model.dto.PdfSettingDto;
import com.elitexplorer.backend.pdfsetting.model.entity.PdfSetting;
import com.elitexplorer.backend.pdfsetting.model.entity.SettingType;

public interface PdfSettingInterface {

    PdfSetting savePdfSetting(PdfSettingDto dto);

    PdfSetting findBySetting(SettingType settingType);
}
