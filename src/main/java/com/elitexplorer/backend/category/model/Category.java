package com.elitexplorer.backend.category.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String category;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;
}
