package com.elitexplorer.backend.toconly.repository;

import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pdf1TocRepository extends JpaRepository<Pdf1Toc,Integer> {
}
