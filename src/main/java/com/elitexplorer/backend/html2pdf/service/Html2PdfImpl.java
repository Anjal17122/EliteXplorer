package com.elitexplorer.backend.html2pdf.service;

import com.elitexplorer.backend.email.model.PdfType;
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
import org.springframework.stereotype.Service;
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

@Service
public class Html2PdfImpl {

    @Autowired
    Pdf1Pdf2DetailRepository repo;

    @Autowired
    Pdf1TocRepository tocRepo;

    @Autowired
    ServletContext servletContext;

    private final TemplateEngine templateEngine;

    public Html2PdfImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    public byte[] getPDFBytes(int id, PdfType pdfType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request, response, servletContext);
        Pdf1Pdf2Generate generate;
        if (pdfType == PdfType.tocitinerary) {
            Pdf1Toc pdf1Toc = tocRepo.findById(id).orElse(null);
            generate = DtoConvert.generateToc(pdf1Toc);
        } else {
            Pdf1 pdf1 = new Pdf1();
            pdf1.setId(id);
            List<Pdf1Pdf2Detail> pdf1Pdf2Details = repo.findByPdf1(pdf1);
            generate = DtoConvert.convert(pdf1Pdf2Details);
            if (pdfType == PdfType.shortitinerary) {
                generate.setPdf2(new ArrayList<>());
                List<Integer> pageNo = generate.getPageNo();
                pageNo.set(2, pageNo.get(1));
                pageNo.set(3, pageNo.get(1) + 1);
                generate.setPageNo(pageNo);
            }
        }
        String filename = generate.getPdf1().getPreparedTo() + ".pdf";
        context.setVariable("pdf", generate);
        String orderHtml = templateEngine.process("index", context);

        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        /* Setup converter properties */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:5050");

        FontProvider fontProvider = new DefaultFontProvider(false, false, false);
        fontProvider.addFont(Constants.fontPath + File.separator + "tahomabd.ttf");
        fontProvider.addFont(Constants.fontPath + File.separator + "lucida.ttf");
        fontProvider.addFont(Constants.fontPath + File.separator + "arial.ttf");
        converterProperties.setFontProvider(fontProvider);

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* Extract output as bytes */
        return target.toByteArray();
    }

}
