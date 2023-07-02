package com.elitexplorer.backend.pdf2.service.impl;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.category.repository.SubCategoryRepository;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.Status;
import com.elitexplorer.backend.pdf2.repository.Pdf2Repository;
import com.elitexplorer.backend.pdf2.service.Interface.Pdf2Interface;
import com.mysql.cj.conf.DatabaseUrlContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pdf2Impl implements Pdf2Interface {

    @Autowired
    Pdf2Repository repo;

    @Autowired
    SubCategoryRepository subCategoryRepo;

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
    public Page<Pdf2> findAll(int page, int size){
        return repo.findAllByOrderByIdDesc(PageRequest.of(page,size));
    }

    @Override
    public Page<Pdf2> getByCategoryId(int id, int page, int offset){
        Category category = subCategoryRepo.findById(id).orElse(null).getCategory();
        return repo.findByCategory(category, Status.active, PageRequest.of(page,offset));
    }

    @Override
    public Page<Pdf2> getBySubCategory(int subCategoryId, int page, int offset){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(subCategoryId);
        return repo.findBySubCategory(subCategory,Status.active,PageRequest.of(page,offset));
    }

    @Override
    public Page<Pdf2> getByTitle(int subCategoryId, String title, int page, int offset){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(subCategoryId);
        return repo.findByName(title,subCategory,Status.active,PageRequest.of(page,offset));
    }

    @Override
    public Page<Pdf2> getById(int subCategoryId, int id, int page, int offset){
        SubCategory subCategory = new SubCategory();
        subCategory.setId(subCategoryId);
        return repo.findById(id,subCategory,Status.active,PageRequest.of(page,offset));
    }

    @Override
    public Pdf2 clonePdf2(int id){
        Pdf2 pdf2 = DtoConvert.convert(repo.findById(id).orElse(null));
        pdf2.setTitle(pdf2.getTitle() + " - Duplicate");
        return repo.save(pdf2);
    }
}
