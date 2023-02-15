package com.elitexplorer.backend.pdf1pdf2detail.model;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Pdf1Pdf2Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pdf1 pdf1;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pdf2 pdf2;

    private int days;
}
