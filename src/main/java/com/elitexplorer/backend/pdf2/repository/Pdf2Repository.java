package com.elitexplorer.backend.pdf2.repository;

import com.elitexplorer.backend.category.model.Category;
import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.pdf2.model.Pdf2;
import com.elitexplorer.backend.pdf2.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf2Repository extends JpaRepository<Pdf2,Integer> {

    List<Pdf2> findAllByOrderByIdDesc();

    Page<Pdf2> findAllByOrderByIdDesc(Pageable pageable);

    @Query("From Pdf2 p where p.subCategory.category=:category and p.status=:status")
    Page<Pdf2> findByCategory(@Param("category") Category category, @Param("status") Status status, Pageable pageable);

    @Query("From Pdf2 p where p.subCategory=:subCategory and status=:status")
    Page<Pdf2> findBySubCategory(@Param("subCategory")SubCategory subCategory, @Param("status") Status status, Pageable pageable);

    @Query("From Pdf2 p where p.title like :name% and p.subCategory=:subCategory and status=:status")
    Page<Pdf2> findByName(@Param("name") String name, @Param("subCategory") SubCategory subCategory, @Param("status") Status status, Pageable pageable);

    @Query("From Pdf2 p where p.id=:id and p.subCategory=:subCategory and status=:status")
    Page<Pdf2> findById(@Param("id") int id, @Param("subCategory") SubCategory subCategory, @Param("status") Status status, Pageable pageable);
}
