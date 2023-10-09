package com.elitexplorer.backend.pricingtemplate.model.dto;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class PricingTemplateDto {

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

    private int pdf1Id;


    private int pdf2Id;

   private int  pdf1Pdf2Id;

    private float total;

    public void calculateTotal() {
        this.total = hotelPrice + flightPrice + guidePrice + transportPrice +
                entrancePrice + permitPrice + mealPrice + extraPrice;
    }
}
