package com.elitexplorer.backend.pdf1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Pdf1GenerateDto {
    private int id;

    private String title;

    private Date startDate;

    private int totalDays;

    private String mainText;

    private String preparedTo;

    private List<String> inclusion;

    private List<String> exclusion;

    private int noOfAdults;

    private long amountPerAdult;

    private int noOfChildren;

    private long amountPerChildren;

    private String currency;

    private String file;

    private long totalAmount;

    private String fullDate;

    private String tripSummaryDate;
}
