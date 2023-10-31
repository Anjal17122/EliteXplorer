package com.elitexplorer.backend.html2pdf.utils;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.category.model.dto.CategoryDto;
import com.elitexplorer.backend.category.model.dto.SubCategoryDto;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1GenerateDto;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.model.dto.Pdf1Pdf2Dto;
import com.elitexplorer.backend.pdf1pdf2detail.model.dto.Pdf1Pdf2Generate;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2Dto;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2GenerateDto;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2TocDto;
import com.elitexplorer.backend.pdfsetting.model.dto.PdfSettingDto;
import com.elitexplorer.backend.pdfsetting.model.entity.PdfSetting;
import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateDto;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplatePdf2Dto;
import com.elitexplorer.backend.toconly.model.dto.Pdf1TocDto;
import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DtoConvert {

    public static Pdf1 convert(Pdf1Dto dto){
        Pdf1 pdf1 = new Pdf1();
        SubCategory subCategory = new SubCategory();
        subCategory.setId(dto.getSubCategoryId());
        pdf1.setId(dto.getId());
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(0);
        pdf1.setAmountPerChildren(0);
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(dto.getStartDate());
        pdf1.setTitle(dto.getTitle());
        pdf1.setTotalDays(dto.getTotalDays());
        pdf1.setFilename(dto.getFile());
        if (dto.getSubCategoryId()!=0) {
            pdf1.setSubCategory(subCategory);
        }else {
            pdf1.setSubCategory(null);
        }
        pdf1.setHint(dto.getHint());
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
        entity.setHint(dto.getHint());
        entity.setTocSubTitle(dto.getTocSubTitle());
        if (dto.getTocTitle()==null || dto.getTocTitle().isEmpty())
            entity.setTocTitle(dto.getTitle());
        if (dto.getTocSubTitle()==null || dto.getTocSubTitle().isEmpty())
            entity.setTocSubTitle(dto.getSubTitle());
        if (dto.getSubCategoryId()!=0) {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(dto.getSubCategoryId());
            entity.setSubCategory(subCategory);
        }else{
            entity.setSubCategory(null);
        }
        entity.setStatus(dto.getStatus());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        return entity;

    }



    public static Pdf2 convert(Pdf2 dto){
        Pdf2 entity = new Pdf2();
        entity.setId(0);
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setText(dto.getText());
        entity.setHotel(dto.getHotel());
        entity.setFood(dto.getFood());
        entity.setRoom(dto.getRoom());
        entity.setWebsite(dto.getWebsite());
        entity.setTocTitle(dto.getTocTitle());
        entity.setTocSubTitle(dto.getTocSubTitle());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        entity.setHint(dto.getHint());
        return entity;

    }

    public static Pdf1 convert(Pdf1 dto)  {
        Pdf1 pdf1 = new Pdf1();
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(dto.getAmountPerAdult());
        pdf1.setAmountPerChildren(dto.getAmountPerChildren());
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(dto.getStartDate());
        pdf1.setTitle(dto.getTitle() + " - Duplicate");
        pdf1.setFilename(dto.getFilename());
        pdf1.setTotalDays(dto.getTotalDays());
        pdf1.setHint(dto.getHint());
        pdf1.setTotal(dto.getTotal());
        pdf1.setTotalWithoutExtra(dto.getTotalWithoutExtra());
        pdf1.setTotalAmountPrecisionError(dto.getTotalAmountPrecisionError());
        pdf1.setTax(dto.getTax());
        pdf1.setBuffer(dto.getBuffer());
        pdf1.setMargin(dto.getMargin());
        return pdf1;
    }
    public static List<String> shortMonth = new ArrayList<>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
    public static List<String> longMonth = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
    public static Pdf1GenerateDto convertPdf1(Pdf1 pdf1){
        Calendar cal = Calendar.getInstance();
        cal.setTime(pdf1.getStartDate());
        Pdf1GenerateDto dto = new Pdf1GenerateDto();

        dto.setExclusion(Arrays.asList(pdf1.getExclusion().split(",,")));
        dto.setInclusion(Arrays.asList(pdf1.getInclusion().split(",,")));
//        inclusion 640 character.. 630 character
        dto.setCurrency(pdf1.getCurrency());
        dto.setMainText(pdf1.getMainText());
        dto.setAmountPerAdult(pdf1.getAmountPerAdult());
        dto.setAmountPerChildren(pdf1.getAmountPerChildren());
        dto.setNoOfAdults(pdf1.getNoOfAdults());
        dto.setNoOfChildren(pdf1.getNoOfChildren());
        dto.setPreparedTo(pdf1.getPreparedTo());
        dto.setStartDate(pdf1.getStartDate());
        dto.setTitle(pdf1.getTitle().toUpperCase());
        dto.setTotalDays(pdf1.getTotalDays());
        dto.setFile(pdf1.getFilename());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(pdf1.getStartDate());
        cal2.add(Calendar.DAY_OF_MONTH , pdf1.getTotalDays()-1);
        dto.setFullDate( shortMonth.get(cal.get(Calendar.MONTH)) + " "+cal.get(Calendar.DAY_OF_MONTH) +", " + cal.get(Calendar.YEAR) +" - "+ shortMonth.get(cal2.get(Calendar.MONTH)) + " "+cal2.get(Calendar.DAY_OF_MONTH) +", " +cal2.get(Calendar.YEAR));
        dto.setTotalAmount(pdf1.getNoOfAdults()* pdf1.getAmountPerAdult() + pdf1.getNoOfChildren()* pdf1.getAmountPerChildren());
        dto.setTripSummaryDate("Nepal /"+longMonth.get(cal.get(Calendar.MONTH)) +" /" +cal.get(Calendar.YEAR));
        return dto;
    }

    public static Pdf2GenerateDto convertPdf2(Pdf2 dto){
        Pdf2GenerateDto entity = new Pdf2GenerateDto();
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setText(dto.getText());
        entity.setHotel(dto.getHotel());
        entity.setFood(dto.getFood());
        entity.setRoom(dto.getRoom());
        entity.setWebsite(dto.getWebsite());
        entity.setTocTitle(dto.getTocTitle());
        entity.setTocSubTitle(dto.getTocSubTitle());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        return entity;
    }

    public static Pdf1Pdf2Generate convert(List<Pdf1Pdf2Detail> details){
        Date date = details.get(0).getPdf1().getStartDate();
        Pdf1Pdf2Generate pdf1Pdf2Generate = new Pdf1Pdf2Generate();
        pdf1Pdf2Generate.setPdf1(convertPdf1(details.get(0).getPdf1()));
        List<Pdf2GenerateDto> pdf2 = new ArrayList<>();
        List<Pdf2TocDto> pdf2Toc = new ArrayList<>();
        for (Pdf1Pdf2Detail detail: details){
            Pdf2GenerateDto pdf = convertPdf2(detail.getPdf2());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date);

            String dateMonth = longMonth.get(cal2.get(Calendar.MONTH)) +" - " +cal2.get(Calendar.DAY_OF_MONTH);
            pdf.setTitle(dateMonth +" "+ pdf.getTitle());
            pdf2.add(pdf);

            Pdf2TocDto pdfToc = new Pdf2TocDto();
            pdfToc.setTocTitle(dateMonth +" "+ detail.getPdf2().getTocTitle());
            pdfToc.setTocSubTitle(detail.getPdf2().getTocSubTitle());
            String days = String.valueOf(detail.getDays());
            if (Integer.parseInt(days)<10){
                days = "0"+days;
            }
            pdfToc.setTocDays(days);
            pdf2Toc.add(pdfToc);


            cal2.add(Calendar.DAY_OF_MONTH , 1);
            date = cal2.getTime();
        }
        pdf1Pdf2Generate.setPdf2(pdf2);
        pdf1Pdf2Generate.setToc(Lists.partition(pdf2Toc, 7));

        List<Integer> pageNo= new ArrayList<>();
        int total = (int) Math.round(pdf2Toc.size()/7);
        if (pdf2Toc.size()%7==0){
            total = total-1;
        }
        pageNo.add(2);
        pageNo.add(3+total);
        pageNo.add(3+total+pdf2Toc.size());
        pageNo.add(4+total+pdf2Toc.size()-1);

        pdf1Pdf2Generate.setPageNo(pageNo);

        return pdf1Pdf2Generate;
    }

    public static TocOnly convert(TocOnlyDto dto){
        Pdf1Toc pdf1Toc = new Pdf1Toc();
        pdf1Toc.setId(dto.getPdf1Toc());
        System.out.println(dto.getPdf1Toc());

        TocOnly tocOnly = new TocOnly();
        tocOnly.setId(dto.getId());
        tocOnly.setPdf1Toc(pdf1Toc);
        tocOnly.setDay(dto.getDay());
        tocOnly.setTitle(dto.getTitle());
        tocOnly.setSubTitle(dto.getSubTitle());
        return tocOnly;
    }

    public static TocOnlyDto convert(TocOnly entity){

        TocOnlyDto tocDto = new TocOnlyDto();
        tocDto.setId(entity.getId());
        tocDto.setPdf1Toc(entity.getPdf1Toc().getId());
        tocDto.setDay(entity.getDay());
        tocDto.setTitle(entity.getTitle());
        tocDto.setSubTitle(entity.getSubTitle());
        return tocDto;
    }

    public static Pdf1Toc convert(Pdf1TocDto dto) {
        Pdf1Toc pdf1 = new Pdf1Toc();
        pdf1.setId(dto.getId());

        SubCategory subCategory = new SubCategory();
        subCategory.setId(dto.getSubCategoryId());
        if(dto.getSubCategoryId()==0){
            pdf1.setSubCategory(null);
        }else {
            pdf1.setSubCategory(subCategory);
        }
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(dto.getAmountPerAdult());
        pdf1.setAmountPerChildren(dto.getAmountPerChildren());
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(dto.getStartDate());
        pdf1.setTitle(dto.getTitle());
        pdf1.setTotalDays(dto.getTotalDays());
        pdf1.setHint(dto.getHint());
        pdf1.setFilename(dto.getFile());
        return pdf1;
    }

    public static Pdf1TocDto convertToDto(Pdf1Toc pdf1) {
        Pdf1TocDto dto = new Pdf1TocDto();
        dto.setId(pdf1.getId());
        dto.setExclusion(pdf1.getExclusion());
        dto.setInclusion(pdf1.getInclusion());
        dto.setCurrency(pdf1.getCurrency());
        dto.setMainText(pdf1.getMainText());
        dto.setAmountPerAdult(pdf1.getAmountPerAdult());
        dto.setAmountPerChildren(pdf1.getAmountPerChildren());
        dto.setNoOfAdults(pdf1.getNoOfAdults());
        dto.setNoOfChildren(pdf1.getNoOfChildren());
        dto.setPreparedTo(pdf1.getPreparedTo());
        dto.setStartDate(pdf1.getStartDate());
        dto.setTitle(pdf1.getTitle());
        dto.setTotalDays(pdf1.getTotalDays());
        dto.setHint(pdf1.getHint());
        dto.setFile(pdf1.getFilename());
        if (pdf1.getSubCategory()!=null) {
            dto.setSubCategoryId(pdf1.getSubCategory().getId());
            dto.setSubCategory(pdf1.getSubCategory().getSubCategory());
        }
        return dto;
    }

    public static Pdf1Dto convertToDto(Pdf1 pdf1) {
        Pdf1Dto dto = new Pdf1Dto();
        dto.setId(pdf1.getId());
        dto.setExclusion(pdf1.getExclusion());
        dto.setInclusion(pdf1.getInclusion());
        dto.setCurrency(pdf1.getCurrency());
        dto.setMainText(pdf1.getMainText());
        dto.setAmountPerAdult(pdf1.getAmountPerAdult());
        dto.setAmountPerChildren(pdf1.getAmountPerChildren());
        dto.setNoOfAdults(pdf1.getNoOfAdults());
        dto.setNoOfChildren(pdf1.getNoOfChildren());
        dto.setPreparedTo(pdf1.getPreparedTo());
        dto.setStartDate(pdf1.getStartDate());
        dto.setTitle(pdf1.getTitle());
        dto.setTotalDays(pdf1.getTotalDays());
        dto.setHint(pdf1.getHint());
        dto.setFile(pdf1.getFilename());
        dto.setTotal(pdf1.getTotal());
        dto.setTax(pdf1.getTax());
        dto.setMargin(pdf1.getMargin());
        dto.setBuffer(pdf1.getBuffer());
        dto.setTotalWithoutExtra(pdf1.getTotalWithoutExtra());
        dto.setTotalAmountPrecisionError(pdf1.getTotalAmountPrecisionError());
        if (pdf1.getSubCategory()!=null) {
            dto.setSubCategory(pdf1.getSubCategory().getSubCategory());
            dto.setSubCategoryId(pdf1.getSubCategory().getId());
        }
        return dto;
    }

    public static Pdf1Toc convert(Pdf1Toc dto){
        Pdf1Toc pdf1 = new Pdf1Toc();
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(dto.getAmountPerAdult());
        pdf1.setAmountPerChildren(dto.getAmountPerChildren());
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(dto.getStartDate());
        pdf1.setTitle(dto.getTitle()+ " -Duplicate");
        pdf1.setTotalDays(dto.getTotalDays());
        pdf1.setHint(dto.getHint());
        pdf1.setFilename(dto.getFilename());
        return pdf1;
    }

    public static Pdf1 convertTransfer(Pdf1Toc dto){
        Pdf1 pdf1 = new Pdf1();
        pdf1.setExclusion(dto.getExclusion());
        pdf1.setInclusion(dto.getInclusion());
        pdf1.setCurrency(dto.getCurrency());
        pdf1.setMainText(dto.getMainText());
        pdf1.setAmountPerAdult(dto.getAmountPerAdult());
        pdf1.setAmountPerChildren(dto.getAmountPerChildren());
        pdf1.setNoOfAdults(dto.getNoOfAdults());
        pdf1.setNoOfChildren(dto.getNoOfChildren());
        pdf1.setPreparedTo(dto.getPreparedTo());
        pdf1.setStartDate(dto.getStartDate());
        pdf1.setTitle(dto.getTitle());
        pdf1.setTotalDays(dto.getTotalDays());
        pdf1.setHint(dto.getHint());
        pdf1.setFilename(dto.getFilename());
        return pdf1;
    }

    public static Pdf1Pdf2Generate generateToc(Pdf1Toc pdf1Toc){
        Date date = pdf1Toc.getStartDate();
        Pdf1Pdf2Generate pdf1Pdf2Generate = new Pdf1Pdf2Generate();
        pdf1Pdf2Generate.setPdf1(convertPdf1(convertTransfer(pdf1Toc)));
        List<Pdf2GenerateDto> pdf2 = new ArrayList<>();
        List<Pdf2TocDto> pdf2Toc = new ArrayList<>();
        for (TocOnly detail: pdf1Toc.getTocOnly()){
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date);

            String dateMonth = longMonth.get(cal2.get(Calendar.MONTH)) +" - " +cal2.get(Calendar.DAY_OF_MONTH);

            Pdf2TocDto pdfToc = new Pdf2TocDto();
            pdfToc.setTocTitle(dateMonth +" "+ detail.getTitle());
            pdfToc.setTocSubTitle(detail.getSubTitle());
            String days = String.valueOf(detail.getDay());
            if (Integer.parseInt(days)<10){
                days = "0"+days;
            }
            pdfToc.setTocDays(days);
            pdf2Toc.add(pdfToc);


            cal2.add(Calendar.DAY_OF_MONTH , 1);
            date = cal2.getTime();
        }
        Comparator<Pdf2TocDto> comparator = new Comparator<Pdf2TocDto>() {
            @Override
            public int compare(Pdf2TocDto dto1, Pdf2TocDto dto2) {
                // Parse tocDays as integers and compare them
                int days1 = Integer.parseInt(dto1.getTocDays());
                int days2 = Integer.parseInt(dto2.getTocDays());
                return Integer.compare(days1, days2);
            }
        };

        // Sort the list using the custom comparator
        Collections.sort(pdf2Toc, comparator);
        pdf1Pdf2Generate.setPdf2(pdf2);
        pdf1Pdf2Generate.setToc(Lists.partition(pdf2Toc, 7));

        List<Integer> pageNo= new ArrayList<>();
        int total = (int) Math.round(pdf2Toc.size()/7);
        if (pdf2Toc.size()%7==0){
            total = total-1;
        }

        if (pdf1Toc.getTocOnly().isEmpty()){
            pageNo.add(2);
            pageNo.add(3);
            pageNo.add(2);
            pageNo.add(2);
        }else{
            pageNo.add(2);
            pageNo.add(3+total);
            pageNo.add(3+total);
            pageNo.add(4+total-1);
        }

        pdf1Pdf2Generate.setPageNo(pageNo);
        return pdf1Pdf2Generate;
    }

    public static CategoryDto convert(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setCategory(category.getCategory());
        dto.setSubCategory(category.getSubCategories().stream().map(DtoConvert::convert).collect(Collectors.toList()));
        return dto;
    }

    public static SubCategoryDto convert(SubCategory entity){
        SubCategoryDto dto = new SubCategoryDto();
        dto.setId(entity.getId());
        dto.setSubCategory(entity.getSubCategory());
        return dto;
    }

//    public static Pdf2Dto convertSend(Pdf2 entity){
//        Pdf2Dto dto = new Pdf2Dto();
//        dto.setId(entity.getId());
//        dto.setTitle(entity.getTitle());
//        dto.setSubCategory(entity.getSubCategory().getSubCategory());
//        return dto;
//
//    }
    public static Pdf2Dto convertSend(Pdf2 pdf2){
        Pdf2Dto dto = new Pdf2Dto();
        dto.setId(pdf2.getId());
        dto.setTitle(pdf2.getTitle());
        dto.setSubTitle(pdf2.getSubTitle());
        dto.setText(pdf2.getText());
        dto.setHotel(pdf2.getHotel());
        dto.setFood(pdf2.getFood());
        dto.setRoom(pdf2.getRoom());
        dto.setWebsite(pdf2.getWebsite());
        dto.setTocTitle(pdf2.getTocTitle());
        dto.setTocSubTitle(pdf2.getTocSubTitle());
        dto.setImage1(pdf2.getImage1());
        dto.setImage2(pdf2.getImage2());
        dto.setHint(pdf2.getHint());
        if (pdf2.getSubCategory()!=null) {
            dto.setSubCategory(pdf2.getSubCategory().getSubCategory());
            dto.setSubCategoryId(pdf2.getSubCategory().getId());
        }
        return dto;
    }

    public static Pdf1Pdf2Dto convert(Pdf1Pdf2Detail detail){
        Pdf1Pdf2Dto dto = new Pdf1Pdf2Dto();
        dto.setDay(detail.getDays());
        dto.setId(detail.getId());
        dto.setPdf1Id(detail.getPdf1().getId());
        if (detail.getPdf2()!=null) {
            dto.setPdf2Id(detail.getPdf2().getId());
        }
        return dto;
    }


    public static PricingTemplateDto convert(PricingTemplate entity) {
        PricingTemplateDto dto = new PricingTemplateDto();
        dto.setId(entity.getId());
        dto.setHotelName(entity.getHotelName());
        dto.setHotelPrice(entity.getHotelPrice());
        dto.setFlightName(entity.getFlightName());
        dto.setFlightPrice(entity.getFlightPrice());
        dto.setGuideName(entity.getGuideName());
        dto.setGuidePrice(entity.getGuidePrice());
        dto.setTransportName(entity.getTransportName());
        dto.setTransportPrice(entity.getTransportPrice());
        dto.setEntranceName(entity.getEntranceName());
        dto.setEntrancePrice(entity.getEntrancePrice());
        dto.setPermitName(entity.getPermitName());
        dto.setPermitPrice(entity.getPermitPrice());
        dto.setMealName(entity.getMealName());
        dto.setMealPrice(entity.getMealPrice());
        dto.setExtraName(entity.getExtraName());
        dto.setExtraPrice(entity.getExtraPrice());
        dto.setPdf1Id(entity.getPdf1() != null ? entity.getPdf1().getId() : 0);
        dto.setPdf2Id(entity.getPdf2() != null ? entity.getPdf2().getId() : 0);
        dto.calculateTotal();

        return dto;
    }

    // Convert PricingTemplateDto to PricingTemplate
    public static PricingTemplate convert(PricingTemplateDto dto) {
        PricingTemplate entity = new PricingTemplate();

        Pdf1 pdf1 = new Pdf1();

        pdf1.setId(dto.getPdf1Id());

        Pdf2 pdf2 = new Pdf2();
        pdf2.setId(dto.getPdf2Id());

        Pdf1Pdf2Detail detail = new Pdf1Pdf2Detail();
        detail.setId(dto.getPdf1Pdf2Id());

        entity.setId(dto.getId());
        entity.setHotelName(dto.getHotelName());
        entity.setHotelPrice(dto.getHotelPrice());
        entity.setFlightName(dto.getFlightName());
        entity.setFlightPrice(dto.getFlightPrice());
        entity.setGuideName(dto.getGuideName());
        entity.setGuidePrice(dto.getGuidePrice());
        entity.setTransportName(dto.getTransportName());
        entity.setTransportPrice(dto.getTransportPrice());
        entity.setEntranceName(dto.getEntranceName());
        entity.setEntrancePrice(dto.getEntrancePrice());
        entity.setPermitName(dto.getPermitName());
        entity.setPermitPrice(dto.getPermitPrice());
        entity.setMealName(dto.getMealName());
        entity.setMealPrice(dto.getMealPrice());
        entity.setExtraName(dto.getExtraName());
        entity.setExtraPrice(dto.getExtraPrice());
        entity.setDetail(detail);

        if (dto.getPdf1Id()!=0) {
            entity.setPdf1(pdf1);
        }else{
            entity.setPdf1(null);
        }
        if (dto.getPdf2Id()!=0) {
            entity.setPdf2(pdf2);
        }else{
            entity.setPdf2(null);
        }

        // You might need to set pdf1 and pdf2 here based on the ids in dto
        return entity;
    }

    public static PricingTemplatePdf2Dto convert(Pdf1Pdf2Detail pdf1Pdf2Detail, PricingTemplate pricingTemplate){
        PricingTemplatePdf2Dto dto = new PricingTemplatePdf2Dto();
        dto.setId(pricingTemplate.getId());
        dto.setHotelName(pricingTemplate.getHotelName());
        dto.setHotelPrice(pricingTemplate.getHotelPrice());
        dto.setFlightName(pricingTemplate.getFlightName());
        dto.setFlightPrice(pricingTemplate.getFlightPrice());
        dto.setGuideName(pricingTemplate.getGuideName());
        dto.setGuidePrice(pricingTemplate.getGuidePrice());
        dto.setTransportName(pricingTemplate.getTransportName());
        dto.setTransportPrice(pricingTemplate.getTransportPrice());
        dto.setEntranceName(pricingTemplate.getEntranceName());
        dto.setEntrancePrice(pricingTemplate.getEntrancePrice());
        dto.setPermitName(pricingTemplate.getPermitName());
        dto.setPermitPrice(pricingTemplate.getPermitPrice());
        dto.setMealName(pricingTemplate.getMealName());
        dto.setMealPrice(pricingTemplate.getMealPrice());
        dto.setExtraName(pricingTemplate.getExtraName());
        dto.setExtraPrice(pricingTemplate.getExtraPrice());
        dto.setPdf1Id(pricingTemplate.getPdf1() != null ? pricingTemplate.getPdf1().getId() : 0);
        dto.setPdf2Id(pricingTemplate.getPdf2() != null ? pricingTemplate.getPdf2().getId() : 0);
        dto.calculateTotal();
        dto.setDay(pdf1Pdf2Detail.getDays());
        return dto;
    }

    public static PricingTemplate convertPricing(PricingTemplate template){
        PricingTemplate entity = new PricingTemplate();
        entity.setId(0);
        entity.setHotelName(template.getHotelName());
        entity.setHotelPrice(template.getHotelPrice());
        entity.setFlightName(template.getFlightName());
        entity.setFlightPrice(template.getFlightPrice());
        entity.setGuideName(template.getGuideName());
        entity.setGuidePrice(template.getGuidePrice());
        entity.setTransportName(template.getTransportName());
        entity.setTransportPrice(template.getTransportPrice());
        entity.setEntranceName(template.getEntranceName());
        entity.setEntrancePrice(template.getEntrancePrice());
        entity.setPermitName(template.getPermitName());
        entity.setPermitPrice(template.getPermitPrice());
        entity.setMealName(template.getMealName());
        entity.setMealPrice(template.getMealPrice());
        entity.setExtraName(template.getExtraName());
        entity.setExtraPrice(template.getExtraPrice());
       return entity;
    }

    public static Pdf2 convertToUpdate(Pdf2 dto) {
        Pdf2 entity = new Pdf2();
        entity.setId(0);
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setText(dto.getText());
        entity.setHotel(dto.getHotel());
        entity.setFood(dto.getFood());
        entity.setRoom(dto.getRoom());
        entity.setWebsite(dto.getWebsite());
        entity.setTocTitle(dto.getTocTitle());
        entity.setTocSubTitle(dto.getTocSubTitle());
        entity.setImage1(dto.getImage1());
        entity.setImage2(dto.getImage2());
        entity.setHint(dto.getHint());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public static PdfSettingDto convert(PdfSetting entity){
        PdfSettingDto dto = new PdfSettingDto();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setSetting(entity.getSetting());
        return dto;
    }

    public static PdfSetting convert(PdfSettingDto entity){
        PdfSetting dto = new PdfSetting();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setSetting(entity.getSetting());
        return dto;
    }

    }
