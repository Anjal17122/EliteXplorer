package com.elitexplorer.backend.pdf1.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf1Repository extends JpaRepository<Pdf1,Integer> {

        List<Pdf1> findAllByOrderByIdDesc();
}
