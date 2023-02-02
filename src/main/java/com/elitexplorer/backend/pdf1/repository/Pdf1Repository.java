package com.elitexplorer.backend.pdf1.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pdf1Repository extends JpaRepository<Pdf1,Integer> {

}
