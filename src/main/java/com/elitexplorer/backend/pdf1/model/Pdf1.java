package com.elitexplorer.backend.pdf1.model;

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

    private String title;

    private Date startDate;

    private int totalDays;

    private String mainText;

    private String preparedTo;

    private String inclusion;

    private String exclusion;

    private int noOfAdults;

    private long amountPerAdult;

    private int noOfChildren;

    private long amountPerChildren;

    private String filename;

    private String currency;

    @OneToMany(mappedBy = "pdf1")
    private List<Pdf1Pdf2Detail> pdf1Pdf2Details;
}
