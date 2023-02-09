package com.elitexplorer.backend.pdf1pdf2detail.service.impl;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.pdf1pdf2detail.service.Interface.Pdf1Pdf2Interface;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.repository.Pdf2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf1Pdf2Impl implements Pdf1Pdf2Interface {

    @Autowired
    Pdf1Pdf2DetailRepository repo;

    @Autowired
    Pdf2Repository pdf2Repository;

    @Override
    public List<Pdf1Pdf2Detail> findAll(Pdf1 pdf1){
        return repo.findByPdf1(pdf1);
    }

    @Override
    public Pdf1Pdf2Detail updateDayDetail(int id, int pdf2Id){

        Pdf1Pdf2Detail pdf1Pdf2Detail = repo.findById(id).orElse(null);
        Pdf2 pdf2 = pdf2Repository.findById(pdf2Id).orElse(null);
        pdf1Pdf2Detail.setPdf2(pdf2);
        return repo.save(pdf1Pdf2Detail);
    }
}
