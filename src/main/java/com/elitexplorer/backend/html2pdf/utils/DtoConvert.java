package com.elitexplorer.backend.html2pdf.utils;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2Dto;

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

    public static Pdf2 convert(Pdf2Dto dto){
        Pdf2 entity = new Pdf2();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setText(dto.getText());
        entity.setHotel(dto.getHotel());
        entity.setFood(dto.getFood());
        entity.setRoom(dto.getRoom());
        entity.setWebsite(dto.getWebsite());
        entity.setTocTitle(dto.getTocTitle());
        entity.setTocSubTitle(dto.getTocSubTitle());
        if (dto.getTocTitle()==null)
            entity.setTocTitle(entity.getTitle());
        if (dto.getTocSubTitle()==null)
            entity.setTocSubTitle(entity.getSubTitle());
        return entity;

    }
}
