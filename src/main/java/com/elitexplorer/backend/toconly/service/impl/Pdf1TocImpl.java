package com.elitexplorer.backend.toconly.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.service.Interface.Pdf1Interface;
import com.elitexplorer.backend.toconly.model.dto.Pdf1TocDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.elitexplorer.backend.toconly.repository.Pdf1TocRepository;
import com.elitexplorer.backend.toconly.repository.TocOnlyRepository;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf1TocImpl implements Pdf1TocInterface {

    @Autowired
    Pdf1TocRepository repo;

    @Autowired
    TocOnlyRepository tocRepo;

    @Autowired
    Pdf1Interface pdf1;

    @Override
    public List<Pdf1Toc> findAll(){
        return repo.findAllByOrderByIdDesc();
    }

    @Override
    public Pdf1Toc findById(int id){
        return repo.findById(id).orElse(new Pdf1Toc());
    }

    @Override
    public Pdf1Toc save(Pdf1Toc toc){
        return repo.save(toc);
    }

    @Override
    public Pdf1Toc clone(int id){
        Pdf1Toc pdf1Toc = repo.findById(id).orElse(null);
        Pdf1Toc saved = repo.save(DtoConvert.convert(pdf1Toc));
        List<TocOnly> getAll= tocRepo.findByPdf1TocOrderByDayDesc(pdf1Toc);
        for (TocOnly tocOnly:getAll){
            TocOnly tocOnly1 = new TocOnly();
            tocOnly1.setPdf1Toc(saved);
            tocOnly1.setTitle(tocOnly.getTitle());
            tocOnly1.setSubTitle(tocOnly.getSubTitle());
            tocOnly1.setDay(tocOnly.getDay());
            tocRepo.save(tocOnly1);
        }
        return saved;
    }

    @Override
    public void transfer(int id){
        Pdf1Toc toc = repo.findById(id).orElse(null);
        pdf1.savePdf1(DtoConvert.convertTransfer(toc));
        tocRepo.deleteByPdf1Toc(toc);
        repo.delete(toc);
    }

}
