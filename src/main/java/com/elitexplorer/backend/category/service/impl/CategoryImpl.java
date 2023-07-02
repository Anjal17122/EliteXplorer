package com.elitexplorer.backend.category.service.impl;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.repository.CategoryRepository;
import com.elitexplorer.backend.category.service.CategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryInterface {

    @Autowired
    CategoryRepository repo;

    @Override
    public List<Category> getAll(){
        return repo.findAll();
    }
}
