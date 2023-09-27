package com.elitexplorer.backend.pricingtemplate.model;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PricingTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String hotelName;

    private float hotelPrice;

    private String flightName;

    private float flightPrice;

    private String guideName;

    private float guidePrice;

    private String transportName;

    private float transportPrice;

    private String entranceName;

    private float entrancePrice;

    private String permitName;

    private float permitPrice;

    private String mealName;

    private float mealPrice;

    private String extraName;

    private float extraPrice;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pdf1 pdf1;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Pdf2 pdf2;

}
