package com.elitexplorer.backend.html2pdf.utils;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DtoConvert {

    public static Pdf1 convert(Pdf1Dto dto) throws ParseException {

        Pdf1 pdf1 = new Pdf1();
        pdf1.setId(dto.getId());
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(dto.getAmountPerAdult());
        pdf1.setAmountPerChildren(dto.getAmountPerChildren());
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getStartDate()));
        pdf1.setTitle(dto.getTitle());
        pdf1.setTotalDays(dto.getTotalDays());
        return pdf1;
    }
}
