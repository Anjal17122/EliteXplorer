package com.elitexplorer.backend.toconly.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TocOnly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String subTitle;

    private int day;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pdf1Toc pdf1Toc;


}
