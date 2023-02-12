package com.elitexplorer.backend.toconly.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TocOnlyRepository extends JpaRepository<TocOnly,Integer> {

    List<TocOnly> findByPdf1TocOrderByDayDesc(Pdf1Toc pdf1);
}
