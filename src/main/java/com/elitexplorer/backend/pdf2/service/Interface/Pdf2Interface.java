package com.elitexplorer.backend.pdf2.service.Interface;

import com.elitexplorer.backend.pdf2.model.Pdf2;

public interface Pdf2Interface {
    public Pdf2 getById(int id);
    public void savePdf1(Pdf2 pdf2);
}
