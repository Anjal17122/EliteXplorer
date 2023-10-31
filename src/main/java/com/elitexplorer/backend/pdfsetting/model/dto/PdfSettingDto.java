package com.elitexplorer.backend.pdfsetting.model.dto;

import com.elitexplorer.backend.pdfsetting.model.entity.SettingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PdfSettingDto {

    private int id;

    private SettingType setting;

    private String value;

}
