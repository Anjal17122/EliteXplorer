package com.elitexplorer.backend.pdf2.repository;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf2Repository extends JpaRepository<Pdf2,Integer> {

    List<Pdf2> findAllByOrderByIdDesc();
}
