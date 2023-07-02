package com.elitexplorer.backend.category.model;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String subCategory;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "subCategory")
    private List<Pdf2> pdf2List;
}
