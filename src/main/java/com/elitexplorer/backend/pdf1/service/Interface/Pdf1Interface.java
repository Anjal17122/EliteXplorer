package com.elitexplorer.backend.pdf1.service.Interface;

import com.elitexplorer.backend.pdf1.model.Pdf1;

import java.util.List;

public interface Pdf1Interface {

    Pdf1 savePdf1(Pdf1 pdf1);

    Pdf1 getById(int id);

    Pdf1 clonePdf(int id);

    List<Pdf1> getALl();
}
