package com.elitexplorer.backend.category.service.impl;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.category.repository.SubCategoryRepository;
import com.elitexplorer.backend.category.service.SubCategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryImpl implements SubCategoryInterface {

    @Autowired
    SubCategoryRepository repo;

    @Override
    public List<SubCategory> getAll(){
        return repo.findAll();
    }

    @Override
    public List<SubCategory> getByCategory(int id){
        Category category = new Category();
        category.setId(id);
        return repo.findByCategory(category);
    }
}
