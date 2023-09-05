package com.elitexplorer.backend.pdf1pdf2detail.service.Interface;

import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;

import java.util.List;

public interface Pdf1Pdf2Interface {

    List<Pdf1Pdf2Detail> findByPdf1Id(int  id);

    Pdf1Pdf2Detail updateDayDetail(int id, int pdf2Id);

    List<Pdf1Pdf2Detail> checkPdf2Null(Pdf1 pdf1);

    boolean downloadAvailable(int id);
}
