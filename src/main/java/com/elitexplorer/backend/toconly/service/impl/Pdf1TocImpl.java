package com.elitexplorer.backend.toconly.service.impl;

import com.elitexplorer.backend.toconly.model.dto.Pdf1TocDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.repository.Pdf1TocRepository;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf1TocImpl implements Pdf1TocInterface {

    @Autowired
    Pdf1TocRepository repo;

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
}
