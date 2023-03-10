package com.elitexplorer.backend.toconly.service.Interface;

import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;

import java.util.List;

public interface Pdf1TocInterface {

    public List<Pdf1Toc> findAll();

     public Pdf1Toc findById(int id);
    public Pdf1Toc save(Pdf1Toc toc);

    public Pdf1Toc clone(int id);

    void transfer(int id);


}
