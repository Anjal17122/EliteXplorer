package com.elitexplorer.backend.toconly.service.impl;

import com.elitexplorer.backend.toconly.repository.Pdf1TocRepository;
import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pdf1TocImpl implements Pdf1TocInterface {

    @Autowired
    Pdf1TocRepository repo;
}
