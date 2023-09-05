package com.elitexplorer.backend.toconly.repository;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf1TocRepository extends JpaRepository<Pdf1Toc,Integer> {

    List<Pdf1Toc> findAllByOrderByIdDesc();

    @Query("From Pdf1Toc p where p.title like :title% order by p.id desc")
    List<Pdf1Toc> findByTitle(@Param("title") String title);

    @Query("From Pdf1Toc p where p.preparedTo like :name% order by p.id desc")
    List<Pdf1Toc> findByName(@Param("name") String name);
}
