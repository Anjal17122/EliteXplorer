package com.elitexplorer.backend.category.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private int id;

    private String category;

    private List<SubCategoryDto> subCategory;
}
