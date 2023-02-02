package com.elitexplorer.backend.pdf1pdf2detail.repository;

import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pdf1Pdf2DetailRepository extends JpaRepository<Pdf1Pdf2Detail,Integer> {
}
