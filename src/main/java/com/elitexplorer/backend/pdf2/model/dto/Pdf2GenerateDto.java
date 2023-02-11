package com.elitexplorer.backend.pdf2.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Pdf2GenerateDto {
    private String title;
    private String subTitle;
    private String text;
    private String image1;
    private String image2;
    private String hotel;
    private String food;
    private String room;
    private String website;
    private String tocTitle;
    private String tocSubTitle;
    private String tocDays;

}
