package com.elitexplorer.backend.pdf1pdf2detail.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Pdf1Pdf2DetailRepository extends JpaRepository<Pdf1Pdf2Detail,Integer> {

    List<Pdf1Pdf2Detail> findByPdf1(Pdf1 pdf1);

    @Modifying
    @Transactional
    @Query("delete from Pdf1Pdf2Detail p where p.pdf1.id=:pdf1")
    void deleteByPdf1(@Param("pdf1") int pdf);

    @Query("From Pdf1Pdf2Detail p where p.pdf2=null and p.pdf1=:pdf1")
    List<Pdf1Pdf2Detail> checkPdf2Null(Pdf1 pdf1);
}
