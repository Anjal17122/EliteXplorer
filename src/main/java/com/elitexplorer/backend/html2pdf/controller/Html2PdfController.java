package com.elitexplorer.backend.html2pdf.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1pdf2detail.model.Pdf1Pdf2Detail;
import com.elitexplorer.backend.pdf1pdf2detail.model.dto.Pdf1Pdf2Generate;
import com.elitexplorer.backend.pdf1pdf2detail.repository.Pdf1Pdf2DetailRepository;
import com.elitexplorer.backend.toconly.model.entity.Pdf1Toc;
import com.elitexplorer.backend.toconly.repository.Pdf1TocRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.layout.font.FontProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class Html2PdfController {

    @Autowired
    Pdf1Pdf2DetailRepository repo;

    @Autowired
    Pdf1TocRepository tocRepo;

    @Autowired
    ServletContext servletContext;

    private final TemplateEngine templateEngine;

    public Html2PdfController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping(path = "/pdf/{toc}/{id}")
    public ResponseEntity<?> getPDF(@PathVariable("id") int id, @PathVariable("toc") int toc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request, response, servletContext);
        Pdf1Pdf2Generate generate;
        if (toc==2){
            Pdf1Toc pdf1Toc = tocRepo.findById(id).orElse(null);
            generate = DtoConvert.generateToc(pdf1Toc);
        }
        else {
            Pdf1 pdf1 = new Pdf1();
            pdf1.setId(id);
            List<Pdf1Pdf2Detail> pdf1Pdf2Details = repo.findByPdf1(pdf1);
            generate = DtoConvert.convert(pdf1Pdf2Details);
            if (toc == 1) {
                generate.setPdf2(new ArrayList<>());
                List<Integer> pageNo = generate.getPageNo();
                pageNo.set(2, pageNo.get(1));
                pageNo.set(3, pageNo.get(1) + 1);
                generate.setPageNo(pageNo);
            }
        }
//+ "(" + generate.getPdf1().getFullDate()+  ")"
        String filename = generate.getPdf1().getPreparedTo() +".pdf";
        context.setVariable("pdf", generate);
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
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }
}
