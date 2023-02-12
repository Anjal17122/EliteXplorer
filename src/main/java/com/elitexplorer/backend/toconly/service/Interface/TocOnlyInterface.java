package com.elitexplorer.backend.toconly.service.Interface;

import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;

import java.util.List;

public interface TocOnlyInterface {

    List<TocOnly> findByPdf1Toc(int id);

    TocOnly save(TocOnlyDto dto);


}
