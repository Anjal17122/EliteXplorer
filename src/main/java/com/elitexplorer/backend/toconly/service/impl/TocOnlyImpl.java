package com.elitexplorer.backend.toconly.service.impl;

import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.elitexplorer.backend.toconly.repository.TocOnlyRepository;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
public class TocOnlyImpl implements TocOnlyInterface {

    @Autowired
    TocOnlyRepository repo;


    @Override
    public List<TocOnly> findByPdf1Toc(int id){
        if (id!=0) {
            Pdf1Toc pdf1Toc = new Pdf1Toc();
            pdf1Toc.setId(id);
            return repo.findByPdf1TocOrderByDayAsc(pdf1Toc);
        }
        return new ArrayList<>();
    }

    @Override
    public Page<TocOnly> findByPdf1Toc(int id, int page, int size){

            Pdf1Toc pdf1Toc = new Pdf1Toc();
            pdf1Toc.setId(id);
            return repo.findByPdf1TocOrderByDayAsc(pdf1Toc, PageRequest.of(page,size));


    }

    @Override
    public void deleteById(int id){
         repo.deleteById(id);
    }
    @Override
    public TocOnly save(TocOnlyDto dto){
//        if (dto.getPdf1Toc()!=0){
            return repo.save(DtoConvert.convert(dto));
//        }
//        return new TocOnly();
    }
}
