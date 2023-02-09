package com.elitexplorer.backend.html2pdf.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.layout.font.FontProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class Html2PdfController {

    @Autowired
    Pdf1Pdf2DetailRepository repo;

    @Autowired
    ServletContext servletContext;

    private final TemplateEngine templateEngine;

    public Html2PdfController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping(path = "/pdf/{id}")
    public ResponseEntity<?> getPDF(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request, response, servletContext);
        Pdf1 pdf1 = new Pdf1();
        pdf1.setId(id);
        List<Pdf1Pdf2Detail> pdf1Pdf2Details = repo.findByPdf1(pdf1);
        context.setVariable("pdf", "order");
        String orderHtml = templateEngine.process("index", context);
        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:5050");

        FontProvider fontProvider = new DefaultFontProvider(false,false,false);
//        fontProvider.addFont(Constants.fontPath+ File.separator + "Roboto-Regular.ttf");
        fontProvider.addFont(Constants.fontPath+ File.separator + "tahomabd.ttf");
        fontProvider.addFont(Constants.fontPath+ File.separator + "lucida.ttf");
        fontProvider.addFont(Constants.fontPath+ File.separator + "arial.ttf");
        converterProperties.setFontProvider(fontProvider);

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }
}
