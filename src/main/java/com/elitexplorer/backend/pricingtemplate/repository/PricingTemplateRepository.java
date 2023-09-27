package com.elitexplorer.backend.pricingtemplate.repository;


import com.elitexplorer.backend.pricingtemplate.model.PricingTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingTemplateRepository extends JpaRepository<PricingTemplate,Integer> {
}
