package com.elitexplorer.backend.pdf1pdf2detail.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf1Pdf2DetailRepository extends JpaRepository<Pdf1Pdf2Detail,Integer> {

    List<Pdf1Pdf2Detail> findByPdf1(Pdf1 pdf1);
}
