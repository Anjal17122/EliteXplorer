package com.elitexplorer.backend.pdf1.model;

import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pdf1 {
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "pdf1")
    private List<Pdf1Pdf2Detail> pdf1Pdf2Details;
}
