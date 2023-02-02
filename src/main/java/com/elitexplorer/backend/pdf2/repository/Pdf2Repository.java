package com.elitexplorer.backend.pdf2.repository;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pdf2Repository extends JpaRepository<Pdf2,Integer> {
}
