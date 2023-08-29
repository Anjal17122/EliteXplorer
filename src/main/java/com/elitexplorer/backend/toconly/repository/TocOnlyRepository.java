package com.elitexplorer.backend.toconly.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TocOnlyRepository extends JpaRepository<TocOnly,Integer> {

    List<TocOnly> findByPdf1TocOrderByDayAsc(Pdf1Toc pdf1);
    Page<TocOnly> findByPdf1TocOrderByDayAsc(Pdf1Toc pdf1, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from TocOnly p where p.pdf1Toc=:pdf1Toc")
    void deleteByPdf1Toc(@Param("pdf1Toc") Pdf1Toc pdfTOc);
}
