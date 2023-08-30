package com.elitexplorer.backend.toconly.service.Interface;

import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Pdf1TocInterface {

    public Page<Pdf1Toc> findAll(int page, int size);

     public Pdf1Toc findById(int id);
    public Pdf1Toc save(Pdf1Toc toc);

    public Pdf1Toc clone(int id);

    void transfer(int id);


}
