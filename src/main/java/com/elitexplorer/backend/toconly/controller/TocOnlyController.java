package com.elitexplorer.backend.toconly.controller;

import com.elitexplorer.backend.toconly.service.Interface.TocOnlyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TocOnlyController {

    @Autowired
    TocOnlyInterface service;
}
