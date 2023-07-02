package com.elitexplorer.backend.pdf2.model;

import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Pdf2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String hint;


    @Lob
    private String title;

    @Lob
    private String subTitle;

    @Lob
    private String text;

    @Lob
    private String image1;

    @Lob
    private String image2;


    private String hotel;


    private String food;


    private String room;


    private String website;

    @Lob
    private String tocTitle;

    @Lob
    private String tocSubTitle;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "pdf2")
    private List<Pdf1Pdf2Detail> pdf1Pdf2Details;

    @Enumerated(EnumType.STRING)
    private Status status;
}
