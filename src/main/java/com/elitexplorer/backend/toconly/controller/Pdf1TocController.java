package com.elitexplorer.backend.toconly.controller;


import com.elitexplorer.backend.toconly.service.Interface.Pdf1TocInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Pdf1TocController {

    @Autowired
    Pdf1TocInterface service;

}
