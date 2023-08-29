package com.elitexplorer.backend.toconly.service.Interface;

import com.elitexplorer.backend.toconly.model.dto.TocOnlyDto;
import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TocOnlyInterface {

    List<TocOnly> findByPdf1Toc(int id);

    TocOnly save(TocOnlyDto dto);

    Page<TocOnly> findByPdf1Toc(int id, int page, int size);

    void deleteById(int id);


}
