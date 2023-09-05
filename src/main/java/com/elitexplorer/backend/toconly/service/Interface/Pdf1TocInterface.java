package com.elitexplorer.backend.toconly.service.Interface;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Pdf1TocInterface {

    public List<Pdf1Toc> findAll();

     public Pdf1Toc findById(int id);
    public Pdf1Toc save(Pdf1Toc toc);

    public Pdf1Toc clone(int id);

    void transfer(int id);

    List<Pdf1Toc> searchByName(String name);

    List<Pdf1Toc> searchByTitle(String title);

    List<Pdf1Toc> searchById(int id);


}
