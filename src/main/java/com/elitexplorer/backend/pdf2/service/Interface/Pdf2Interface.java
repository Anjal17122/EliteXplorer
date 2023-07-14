package com.elitexplorer.backend.pdf2.service.Interface;

import com.elitexplorer.backend.pdf2.model.Pdf2;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Pdf2Interface {
    public Pdf2 getById(int id);
    public Pdf2 savePdf1(Pdf2 pdf2);

    List<Pdf2> findAll();

    Page<Pdf2> findAll(int page, int size);

    Page<Pdf2> getByCategoryId(int id, int page, int offset);

    Page<Pdf2> getBySubCategory(int subCategoryId, int page, int offset);

    Page<Pdf2> getByTitle(int subCategoryId, String title, int page, int offset);

    Page<Pdf2> getById(int subCategoryId, int id, int page, int offset);

    Pdf2 clonePdf2(int id);
}
