package com.elitexplorer.backend.pdf1.service.Interface;

import com.elitexplorer.backend.pdf1.model.Pdf1;

import java.util.List;

public interface Pdf1Interface {

    Pdf1 savePdf1(Pdf1 pdf1);

    Pdf1 getById(int id);

    List<Pdf1> findBySubCategory(int id);

    List<Pdf1> searchByName(String name);

    List<Pdf1> searchByTitle(String title);

    List<Pdf1> searchById(int id);

    Pdf1 clonePdf(int id);

    void deleteById(int id);

    List<Pdf1> getALl();
}
