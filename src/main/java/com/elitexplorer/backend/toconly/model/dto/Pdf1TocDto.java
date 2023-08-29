package com.elitexplorer.backend.toconly.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class Pdf1TocDto {

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

    private String file;
}
