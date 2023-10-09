package com.elitexplorer.backend.pdf1.service.impl;

import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.exception.SendErrorMessageCustom;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import com.elitexplorer.backend.pdf1.repository.Pdf1Repository;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.repository.Pdf2Repository;
import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import com.elitexplorer.backend.pricingtemplate.repository.PricingTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Pdf1Impl implements Pdf1Interface {

    @Autowired
    Pdf1Repository repo;

    @Autowired
    Pdf1Pdf2DetailRepository detailRepository;

    @Autowired
    PricingTemplateRepository pricingRepository;
    @Autowired
    Pdf2Repository pdf2Repository;


    @Override
    public List<Pdf1> findBySubCategory(int id){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return repo.findBySubCategory(subCategory);
    }

    @Override
    public List<Pdf1> searchByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Pdf1> searchById(int id) {
        List<Pdf1> searchById = new ArrayList<>();
        searchById.add(repo.findById(id).orElseThrow(()->new SendErrorMessageCustom("ID Not Found")));
        return searchById;
    }



    @Override
    public List<Pdf1> searchByTitle(String title) {
        return repo.findByTitle(title);
    }



    public Pdf1 savePdf1(Pdf1 pdf1){

        if (pdf1.getId()==0) {
            Pdf1 savedPdf = repo.save(pdf1);
            for (int i = 1; i <= savedPdf.getTotalDays(); i++) {
                Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
                pdf1Pdf2Detail.setPdf1(savedPdf);
                pdf1Pdf2Detail.setDays(i);
                detailRepository.save(pdf1Pdf2Detail);
            }
            return savedPdf;
        }else{
            Pdf1 previousPdf = repo.findById(pdf1.getId()).orElse(null);
            pdf1.setTax(previousPdf.getTax());
            pdf1.setBuffer(previousPdf.getBuffer());
            pdf1.setMargin(previousPdf.getMargin());
            Pdf1 savedPdf = repo.save(pdf1);

            List<Pdf1Pdf2Detail> pdf1Pdf2Details = detailRepository.findByPdf1OrderByDaysDesc(savedPdf);
            if (pdf1Pdf2Details.get(0).getDays()>savedPdf.getTotalDays()){
                List<Pdf1Pdf2Detail> daysToDelete = detailRepository.findByAfterDays(savedPdf.getTotalDays());
                for (Pdf1Pdf2Detail d: daysToDelete){
                    PricingTemplate template = pricingRepository.findByDetail(d);
                    if (template!=null){
                        pricingRepository.delete(template);
                    }
                    detailRepository.delete(d);
                }
            }else{
                int startDay = pdf1Pdf2Details.get(0).getDays() +1 ;
                for (int i = startDay; i<=savedPdf.getTotalDays(); i++){
                    Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
                    pdf1Pdf2Detail.setPdf1(savedPdf);
                    pdf1Pdf2Detail.setDays(i);
                    detailRepository.save(pdf1Pdf2Detail);
                }
            }
            updatePdf1(savedPdf);
            return savedPdf;

        }
    }

    public Pdf1 clonePdf(int id){
        Pdf1 pdf11 = repo.findById(id).orElse(null);
        List<Pdf1Pdf2Detail> pdf1Pdf2Details = detailRepository.findByPdf1(pdf11);

        Pdf1 pdf1 = repo.save(DtoConvert.convert(pdf11));
        for(Pdf1Pdf2Detail detail: pdf1Pdf2Details){
            Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
            Pdf2 newPdf2 = DtoConvert.convertToUpdate(pdf2Repository.findById(detail.getPdf2().getId()).orElse(null));
            pdf1Pdf2Detail.setPdf2(pdf2Repository.save(newPdf2));
//            pdf1Pdf2Detail.setPdf2(detail.getPdf2());
            pdf1Pdf2Detail.setPdf1(pdf1);
            pdf1Pdf2Detail.setDays(detail.getDays());
            Pdf1Pdf2Detail detailPdf = detailRepository.save(pdf1Pdf2Detail);
            PricingTemplate pricingTemplate = pricingRepository.findByDetail(detail);
            if (pricingTemplate!=null){
                    PricingTemplate price = DtoConvert.convertPricing(pricingTemplate);
                    price.setPdf1(pdf1);
                    price.setDetail(detailPdf);
                    pricingRepository.save(price);
            }
        }
        return pdf11;
    }



    @Override
    public Pdf1 getById(int id) {
        Pdf1 pdf1 = new Pdf1();
        return repo.findById(id).orElse(pdf1);
    }

    @Override
    public List<Pdf1> getALl(){
        return repo.findAllByOrderByIdDesc();
    }

    @Override
    public void deleteById(int id){
        Pdf1 pdf = new Pdf1();
        pdf.setId(id);
        pricingRepository.deleteAll(pricingRepository.findByPdf1(pdf));
       detailRepository.deleteAll(detailRepository.findByPdf1(pdf));
       repo.delete(pdf);
    }

    @Override
    public Pdf1 updatePrices(Pdf1Dto pdf1Dto){
        Pdf1 pdf1 = repo.findById(pdf1Dto.getId()).orElse(null);

       pdf1.setTax(pdf1Dto.getTax());
       pdf1.setBuffer(pdf1Dto.getBuffer());
       pdf1.setMargin(pdf1Dto.getMargin());
       updatePdf1(repo.save(pdf1));
        return pdf1;
    }

    private void updatePdf1(Pdf1 pdf1){

        float total = 0;
        List<PricingTemplate>  allData= pricingRepository.findByPdf1(pdf1);
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
        repo.save(pdf1);
    }

    private float calculateTotal(PricingTemplate pricing) {
        return pricing.getHotelPrice() + pricing.getFlightPrice() + pricing.getGuidePrice() + pricing.getTransportPrice() +
                pricing.getEntrancePrice() + pricing.getPermitPrice() + pricing.getMealPrice() + pricing.getExtraPrice();
    }

}
