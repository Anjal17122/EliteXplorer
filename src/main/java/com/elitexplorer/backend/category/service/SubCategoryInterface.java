package com.elitexplorer.backend.category.service;

import com.elitexplorer.backend.category.model.SubCategory;

import java.util.List;

public interface SubCategoryInterface {

    List<SubCategory> getByCategory(int id);

    List<SubCategory> getAll();
}
