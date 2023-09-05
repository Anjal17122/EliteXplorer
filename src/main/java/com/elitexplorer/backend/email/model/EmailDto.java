package com.elitexplorer.backend.email.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class EmailDto {

    private int pdfId;

    private String subject;

    private String emailTo;

    private String message;

    private PdfType pdfType;
}
