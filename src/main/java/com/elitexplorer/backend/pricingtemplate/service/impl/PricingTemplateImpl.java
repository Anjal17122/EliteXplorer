package com.elitexplorer.backend.pricingtemplate.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.exception.SendErrorMessageCustom;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.repository.Pdf1Repository;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateDto;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplatePdf2Dto;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateWithDetailDto;
import com.elitexplorer.backend.pricingtemplate.repository.PricingTemplateRepository;
import com.elitexplorer.backend.pricingtemplate.service.Interface.PricingTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricingTemplateImpl implements PricingTemplateInterface {



    @Autowired
    PricingTemplateRepository repository;

    @Autowired
    Pdf1Repository pdf1Repository;

    @Autowired
    Pdf1Pdf2DetailRepository pdf1Pdf2DetailRepository;


    @Override
    public PricingTemplate saveDetail(PricingTemplateDto dto){
        Pdf1Pdf2Detail pdf1Pdf2 = new Pdf1Pdf2Detail();
        pdf1Pdf2.setId(dto.getPdf1Pdf2Id());

        PricingTemplate pricingTemplate = repository.findByDetail(pdf1Pdf2);
        if (pricingTemplate!=null){
            if (dto.getHotelName() != null) {
                pricingTemplate.setHotelName(dto.getHotelName());
                pricingTemplate.setHotelPrice(dto.getHotelPrice());
            }

//            if (dto.getHotelPrice() != 0f) {
//
//            }

            if (dto.getFlightName() != null) {
                pricingTemplate.setFlightName(dto.getFlightName());
                pricingTemplate.setFlightPrice(dto.getFlightPrice());

            }

//            if (dto.getFlightPrice() != 0f) {
//            }

            if (dto.getGuideName() != null) {
                pricingTemplate.setGuideName(dto.getGuideName());
                pricingTemplate.setGuidePrice(dto.getGuidePrice());
            }

//            if (dto.getGuidePrice() != 0f) {
//
//            }

            if (dto.getTransportName() != null) {
                pricingTemplate.setTransportName(dto.getTransportName());
                pricingTemplate.setTransportPrice(dto.getTransportPrice());
            }

//            if (dto.getTransportPrice() != 0f) {
//            }

            if (dto.getEntranceName() != null) {
                pricingTemplate.setEntranceName(dto.getEntranceName());
                pricingTemplate.setEntrancePrice(dto.getEntrancePrice());
            }

//            if (dto.getEntrancePrice() != 0f) {
//            }

            if (dto.getPermitName() != null) {
                pricingTemplate.setPermitName(dto.getPermitName());
                pricingTemplate.setPermitPrice(dto.getPermitPrice());
            }
//
//            if (dto.getPermitPrice() != 0f) {
//            }

            if (dto.getMealName() != null) {
                pricingTemplate.setMealName(dto.getMealName());
                pricingTemplate.setMealPrice(dto.getMealPrice());
            }

//            if (dto.getMealPrice() != 0f) {
//            }

            if (dto.getExtraName() != null) {
                pricingTemplate.setExtraName(dto.getExtraName());
                pricingTemplate.setExtraPrice(dto.getExtraPrice());
            }

//            if (dto.getExtraPrice() != 0f) {
//            }
            PricingTemplate savedTemplate =  repository.save(pricingTemplate);
            updatePdf1(savedTemplate);
            return savedTemplate;
        }
        PricingTemplate savedTemplate = repository.save(DtoConvert.convert(dto));
        updatePdf1(savedTemplate);
        return savedTemplate;
    }

    private float calculateTotal(PricingTemplate pricing) {
        return pricing.getHotelPrice() + pricing.getFlightPrice() + pricing.getGuidePrice() + pricing.getTransportPrice() +
                 pricing.getEntrancePrice() + pricing.getPermitPrice() + pricing.getMealPrice() + pricing.getExtraPrice();
    }

    private void updatePdf1(PricingTemplate pricing){
        Pdf1 pdf1 = pdf1Repository.findById(pricing.getPdf1().getId()).orElseThrow(()->{throw new SendErrorMessageCustom("Couldn't Find Id");
        });
        float total = 0;
        List<PricingTemplate>  allData= repository.findByPdf1(pdf1);
        for (PricingTemplate a : allData){
            total = total + calculateTotal(a);
        }
        pdf1.setTotalWithoutExtra(total);
        float bufferAmount = pdf1.getBuffer()/100 * total;
        float marginAmount = pdf1.getMargin()/100 * (total+bufferAmount);
        float totalWithoutTax = total + bufferAmount + marginAmount;
        float totalWithTax = pdf1.getTax()/100 * totalWithoutTax;
        total = total + totalWithTax + bufferAmount + marginAmount;
        int children = pdf1.getNoOfChildren();
        int adult = pdf1.getNoOfAdults();

        int amountPerAdult = Math.round(total/adult);
        int amountPerChildren = 0;

        if (children>0){
            amountPerChildren = Math.round(total/(children + 2*adult));
            amountPerAdult = Math.round((total/(children + 2*adult))*2);
        }

        int finalPrice = children*amountPerChildren + adult* amountPerAdult;
        int precisionError =  finalPrice - Math.round(total);

        pdf1.setAmountPerChildren(amountPerChildren);
        pdf1.setAmountPerAdult(amountPerAdult);
        pdf1.setTotal(finalPrice);
        pdf1.setTotalAmountPrecisionError(precisionError);
        pdf1Repository.save(pdf1);
    }

    @Override
    public PricingTemplateWithDetailDto getDetailByPdf1Id(int pdf1Id){
        Pdf1 pdf1 = pdf1Repository.findById(pdf1Id).orElseThrow(()->{throw new SendErrorMessageCustom("Id Not Found");});
        List<Pdf1Pdf2Detail> pdf1Pdf2Details = pdf1Pdf2DetailRepository.findByPdf1(pdf1);
        List<PricingTemplatePdf2Dto> detailDtos = pdf1Pdf2Details.stream().map((a)->DtoConvert.convert(a,getByPdf1Pdf2(a.getId()))).collect(Collectors.toList());
        PricingTemplateWithDetailDto sendBody = new PricingTemplateWithDetailDto();
        sendBody.setPdf1(DtoConvert.convertToDto(pdf1));
        sendBody.setPricingDetails(detailDtos);
        return sendBody;
    }
    @Override
    public PricingTemplate getByPdf1Pdf2(int id){
        Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
        pdf1Pdf2Detail.setId(id);
        PricingTemplate finala= repository.findByDetail(pdf1Pdf2Detail);
        if (finala!=null){
            return finala;
        }
        return new PricingTemplate();
    }
}
