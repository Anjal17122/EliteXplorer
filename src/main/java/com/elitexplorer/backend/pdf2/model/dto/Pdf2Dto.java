package com.elitexplorer.backend.pdf2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pdf2Dto {

    private int id;

    private String title;

    private String subTitle;

    private String text;

    private MultipartFile image1;
    private MultipartFile image2;
    private String hotel;
    private String food;
    private String room;
    private String website;
    private String tocTitle;
    private String tocSubTitle;
}
