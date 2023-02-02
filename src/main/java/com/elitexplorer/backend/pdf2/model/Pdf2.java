package com.elitexplorer.backend.pdf2.model;

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

    private String title;

    private String subTitle;

    private String text;

    private String image1;

    private String image2;

    private String hotel;

    private String food;

    private String room;

    private String website;

    private String tocTitle;

    private String tocSubTitle;

    @OneToMany(mappedBy = "pdf2")
    private List<Pdf1Pdf2Detail> pdf1Pdf2Details;
}
