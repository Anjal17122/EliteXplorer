package com.elitexplorer.backend.pdf1pdf2detail.model.dto;

import com.elitexplorer.backend.pdf1.model.dto.Pdf1GenerateDto;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2GenerateDto;
import com.elitexplorer.backend.pdf2.model.dto.Pdf2TocDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class Pdf1Pdf2Generate {

    private Pdf1GenerateDto pdf1;

    private List<Pdf2GenerateDto> pdf2;

    private List<List<Pdf2TocDto>> toc;

    private List<Integer> pageNo;

    private boolean detail;
}
