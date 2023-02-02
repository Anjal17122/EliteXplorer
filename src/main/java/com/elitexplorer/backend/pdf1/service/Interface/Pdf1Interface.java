package com.elitexplorer.backend.pdf1.service.Interface;

import com.elitexplorer.backend.pdf1.model.Pdf1;

public interface Pdf1Interface {

    void savePdf1(Pdf1 pdf1);

    Pdf1 getById(int id);
}
