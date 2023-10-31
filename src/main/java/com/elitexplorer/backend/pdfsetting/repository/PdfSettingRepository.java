package com.elitexplorer.backend.pdfsetting.repository;

import com.elitexplorer.backend.pdfsetting.model.entity.PdfSetting;
import com.elitexplorer.backend.pdfsetting.model.entity.SettingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfSettingRepository extends JpaRepository<PdfSetting,Integer> {

    PdfSetting findFirstBySetting(SettingType type);

}
