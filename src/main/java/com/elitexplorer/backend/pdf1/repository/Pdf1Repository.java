package com.elitexplorer.backend.pdf1.repository;

import com.elitexplorer.backend.category.model.SubCategory;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pdf1Repository extends JpaRepository<Pdf1,Integer> {

        List<Pdf1> findAllByOrderByIdDesc();

        List<Pdf1> findBySubCategory(SubCategory subCategory);

        @Query("From Pdf1 p where p.title like :title% order by p.id desc")
        List<Pdf1> findByTitle(@Param("title") String title);

        @Query("From Pdf1 p where p.preparedTo like :name% order by p.id desc")
        List<Pdf1> findByName(@Param("name") String name);

//        @Query("From Pdf1 p where p.id like :id% order by p.id desc")
//        List<Pdf1> searchById(@Param("id") int id);


}
