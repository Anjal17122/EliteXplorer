package com.elitexplorer.backend.category.repository;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {

    List<SubCategory> findByCategory(Category category);
}
