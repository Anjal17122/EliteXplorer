package com.elitexplorer.backend.toconly.model.entity;

import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Pdf1Toc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Lob
    private String title;

    @Lob
    private String hint;


    private Date startDate;

    private int totalDays;

    @Lob
    private String mainText;

    @Lob
    private String preparedTo;

    @Lob
    private String inclusion;

    @Lob
    private String exclusion;

    private int noOfAdults;

    private long amountPerAdult;

    private int noOfChildren;

    private long amountPerChildren;

    @Lob
    private String filename;

    private String currency;

    private boolean allPdf2Uploaded;

    @OneToMany(mappedBy = "pdf1Toc")
    private List<TocOnly> tocOnly;
}
