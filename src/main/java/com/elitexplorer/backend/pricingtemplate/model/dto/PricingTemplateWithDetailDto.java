package com.elitexplorer.backend.pricingtemplate.model.dto;

import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricingTemplateWithDetailDto {

    private Pdf1Dto pdf1;

    private List<PricingTemplatePdf2Dto> pricingDetails;



}
