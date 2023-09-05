package com.elitexplorer.backend.email.service.impl;

import com.elitexplorer.backend.email.model.EmailDto;
import com.elitexplorer.backend.email.service.EmailInterface;
import com.elitexplorer.backend.html2pdf.service.Html2PdfImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Service
public class EmailImpl implements EmailInterface {


    @Autowired
    Html2PdfImpl service;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(EmailDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        byte[] pdfBytes = service.getPDFBytes(dto.getPdfId(), dto.getPdfType(), request, response);
        sendEmailWithAttachment(pdfBytes,dto);
    }



    public void sendEmailWithAttachment(byte[] pdfBytes, EmailDto dto) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(dto.getEmailTo());
        helper.setSubject(dto.getSubject());
        helper.setText(dto.getMessage());

        // Attach the PDF
        helper.addAttachment("document.pdf", new ByteArrayResource(pdfBytes));

        mailSender.send(message);
    }
}
