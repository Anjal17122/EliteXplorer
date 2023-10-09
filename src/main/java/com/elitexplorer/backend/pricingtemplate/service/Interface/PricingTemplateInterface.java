package com.elitexplorer.backend.pricingtemplate.service.Interface;

import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateDto;
import com.elitexplorer.backend.pricingtemplate.model.dto.PricingTemplateWithDetailDto;

public interface PricingTemplateInterface {

    PricingTemplate saveDetail(PricingTemplateDto dto);

    PricingTemplate getByPdf1Pdf2(int id);

    PricingTemplateWithDetailDto getDetailByPdf1Id(int pdf1Id);
}
