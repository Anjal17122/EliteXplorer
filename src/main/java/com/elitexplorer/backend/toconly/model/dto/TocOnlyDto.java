package com.elitexplorer.backend.toconly.model.dto;

import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class TocOnlyDto {

    private int id;

    private String title;

    private String subTitle;

    private int day;

    private int pdf1Toc;

}
