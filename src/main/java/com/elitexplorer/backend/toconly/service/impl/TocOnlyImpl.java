package com.elitexplorer.backend.toconly.service.impl;

import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import com.elitexplorer.backend.toconly.repository.TocOnlyRepository;
import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TocOnlyImpl implements TocOnlyInterface {

    @Autowired
    TocOnlyRepository repo;

}
