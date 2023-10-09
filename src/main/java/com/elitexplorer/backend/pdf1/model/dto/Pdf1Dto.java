package com.elitexplorer.backend.pdf1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class Pdf1Dto {
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

    private String currency;

    private String hint;

    private String subCategory;

    private int subCategoryId;

    private String file;

    private int total;

    private float totalWithoutExtra;

    private float tax;

    private float buffer;

    private float margin;

    private int totalAmountPrecisionError;
}
