package com.elitexplorer.backend.pdf2.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.repository.Pdf2Repository;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf2Impl implements Pdf2Interface {

    @Autowired
    Pdf2Repository repo;

    @Override
    public void savePdf1(Pdf2 pdf2){
      repo.save(pdf2);
    }

    @Override
    public Pdf2 getById(int id) {
        Pdf2 pdf2 = new Pdf2();
        return repo.findById(id).orElse(pdf2);
    }

    @Override
    public List<Pdf2> findAll(){
        return repo.findAllByOrderByIdDesc();
    }

    @Override
    public Pdf2 clonePdf2(int id){
        Pdf2 pdf2 = DtoConvert.convert(repo.findById(id).orElse(null));
        pdf2.setTitle(pdf2.getTitle() + " - Duplicate");
        return repo.save(pdf2);
    }
}
