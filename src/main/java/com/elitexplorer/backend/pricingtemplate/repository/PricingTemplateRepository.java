package com.elitexplorer.backend.pricingtemplate.repository;


import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricingTemplateRepository extends JpaRepository<PricingTemplate,Integer> {

    PricingTemplate findByDetail(Pdf1Pdf2Detail detail);

    List<PricingTemplate> findByPdf1(Pdf1 pdf1);


}
