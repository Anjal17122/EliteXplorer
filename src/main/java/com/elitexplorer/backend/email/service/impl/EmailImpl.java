package com.elitexplorer.backend.email.service.impl;

import com.elitexplorer.backend.email.model.EmailDto;
import com.elitexplorer.backend.email.service.EmailInterface;
import com.elitexplorer.backend.html2pdf.service.Html2PdfImpl;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EmailImpl implements EmailInterface {


    @Autowired
    Html2PdfImpl service;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(EmailDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, DocumentException {
        byte[] pdfBytes = service.getPDFBytes(dto.getPdfId(), dto.getPdfType(), request, response);
        double sizeInMB = (double) pdfBytes.length / (1024 * 1024);
        sendMailWithAttachment(pdfBytes, dto);
    }






    public void sendMailWithAttachment(byte[] pdfBytes, EmailDto dto)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            System.out.println("SENDIIIIIIIIIIIIIIIIIIIIIIIGGGGGGG");
            System.out.println("\""+dto.getEmailTo()+"\"");
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("santosh@elitexplorer.com");
            mimeMessageHelper.setTo(dto.getEmailTo());
            mimeMessageHelper.setText(dto.getMessage());
            mimeMessageHelper.setSubject(dto.getSubject());


            ClassPathResource logo = new ClassPathResource("static/images/emaillogo1.png");

            // Add the logo as an inline image with a Content-ID
            String replacedBody = dto.getMessage().replaceAll("\n", "<br/>");
            String htmlContent = "<html><body>" +
                    "<div style='font-size:16px;'>" + replacedBody + "<br/><br/>Warm Regards,</div>" +
                    "<div><img src='cid:logo' style='width:300px; height:100px;' alt='Company Logo'></div>" +
                    "</body></html>";

            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.addInline("logo", logo);

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File("C:\\Users\\Dell\\Pictures\\images\\mainlogo1.png"));
            mimeMessageHelper.addAttachment("itinerary.pdf", new ByteArrayResource(pdfBytes));
//            mimeMessageHelper.addAttachment(
//                    "mainlogo1.png", file);

            // Sending the mail
            mailSender.send(mimeMessage);
            System.out.println( "Mail sent Successfully");
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {
            System.out.println(e.getMessage());
            // Display message when exception occurred
            System.out.println( "Error while sending mail!!!");
        }
    }




}