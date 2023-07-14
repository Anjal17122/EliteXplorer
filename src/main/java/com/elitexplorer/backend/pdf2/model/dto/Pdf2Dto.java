package com.elitexplorer.backend.pdf2.model.dto;

import com.elitexplorer.backend.pdf2.model.Status;
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

    private String hint;

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

    private Status status;

    private String subCategory;

    private int subCategoryId;
}
