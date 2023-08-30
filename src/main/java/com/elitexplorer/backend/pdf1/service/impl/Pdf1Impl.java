package com.elitexplorer.backend.pdf1.service.impl;

import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.repository.Pdf1Repository;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf1Impl implements Pdf1Interface {

    @Autowired
    Pdf1Repository repo;

    @Autowired
    Pdf1Pdf2DetailRepository detailRepository;


    @Override
    public List<Pdf1> findBySubCategory(int id){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return repo.findBySubCategory(subCategory);
    }

    public Pdf1 savePdf1(Pdf1 pdf1){
        Pdf1 pdf11 = repo.save(pdf1);
        if (pdf1.getId()==0) {
            for (int i = 1; i <= pdf11.getTotalDays(); i++) {
                Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
                pdf1Pdf2Detail.setPdf1(pdf11);
                pdf1Pdf2Detail.setDays(i);
                detailRepository.save(pdf1Pdf2Detail);
            }
        }else{
            List<Pdf1Pdf2Detail> pdf1Pdf2Details = detailRepository.findByPdf1(pdf11);
            detailRepository.deleteByPdf1(pdf11.getId());
            int j = 0;
            for (int i = 1; i <= pdf11.getTotalDays(); i++) {
                Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
                pdf1Pdf2Detail.setPdf1(pdf11);
                pdf1Pdf2Detail.setDays(i);
                try {
                    pdf1Pdf2Detail.setPdf2(pdf1Pdf2Details.get(j).getPdf2());
                }catch (IndexOutOfBoundsException ex){
                    System.out.println("Added extra");
                }
                j++;
                detailRepository.save(pdf1Pdf2Detail);
            }

        }
        return pdf11;
    }

    public Pdf1 clonePdf(int id){
        Pdf1 pdf11 = repo.findById(id).orElse(null);
        List<Pdf1Pdf2Detail> pdf1Pdf2Details = detailRepository.findByPdf1(pdf11);

        Pdf1 pdf1 = repo.save(DtoConvert.convert(pdf11));
        for(Pdf1Pdf2Detail detail: pdf1Pdf2Details){
            Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
            pdf1Pdf2Detail.setPdf2(detail.getPdf2());
            pdf1Pdf2Detail.setPdf1(pdf1);
            pdf1Pdf2Detail.setDays(detail.getDays());
            detailRepository.save(pdf1Pdf2Detail);
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
}
