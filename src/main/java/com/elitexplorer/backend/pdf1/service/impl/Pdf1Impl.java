package com.elitexplorer.backend.pdf1.service.impl;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.repository.Pdf1Repository;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pdf1Impl implements Pdf1Interface {

    @Autowired
    Pdf1Repository repo;

    @Autowired
    Pdf1Pdf2DetailRepository detailRepository;
    public void savePdf1(Pdf1 pdf1){
        Pdf1 pdf11 = repo.save(pdf1);
        for (int i = 1; i<=pdf11.getTotalDays(); i++){
            Pdf1Pdf2Detail pdf1Pdf2Detail = new Pdf1Pdf2Detail();
            pdf1Pdf2Detail.setPdf1(pdf11);
            pdf1Pdf2Detail.setDays(i);
            detailRepository.save(pdf1Pdf2Detail);
        }
    }

    @Override
    public Pdf1 getById(int id) {
        Pdf1 pdf1 = new Pdf1();
        return repo.findById(id).orElse(pdf1);
    }
}
