package com.elitexplorer.backend.email.service.impl;

import com.elitexplorer.backend.email.model.EmailDto;
import com.elitexplorer.backend.email.service.EmailInterface;
import com.elitexplorer.backend.html2pdf.service.Html2PdfImpl;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.text.DocumentException;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import com.itextpdf.layout.element.Image;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDResources;

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
        System.out.println("Before Compressing Size"+ sizeInMB);
        byte[] compressedPdf = manipulatePdf(pdfBytes,0.2f);

        double compressedSize = (double) compressedPdf.length / (1024 * 1024);
        System.out.println("After Compressing Size" + compressedSize);
//        sendMailWithAttachment(pdfBytes, dto);
    }



    public byte[] manipulatePdf(byte[] inputPdf, Float resizeFactor) throws IOException {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(inputPdf)), new PdfWriter(new ByteArrayOutputStream()))) {
            // Iterate over all pages to get all images.
            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                PdfPage page = pdfDoc.getPage(i);
                PdfDictionary pageDict = page.getPdfObject();
                PdfDictionary resources = pageDict.getAsDictionary(PdfName.Resources);

                // Get images
                PdfDictionary xObjects = resources.getAsDictionary(PdfName.XObject);
                if (xObjects == null) {
                    continue;
                }

                Iterator<PdfName> iter = xObjects.keySet().iterator();
                while (iter.hasNext()) {
                    // Get image
                    PdfName imgRef = iter.next();
                    PdfStream stream = xObjects.getAsStream(imgRef);
                    PdfImageXObject image = new PdfImageXObject(stream);

                    // Convert the image to a BufferedImage
                    BufferedImage bi = image.getBufferedImage();
                    if (bi == null) {
                        continue;
                    }

                    // Create a new image with the specified resize factor
                    int width = (int) (bi.getWidth() * resizeFactor);
                    int height = (int) (bi.getHeight() * resizeFactor);
                    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    AffineTransform at = AffineTransform.getScaleInstance(resizeFactor, resizeFactor);
                    java.awt.Graphics2D g = img.createGraphics();
                    g.drawRenderedImage(bi, at);
                    ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();

                    // Write the new image as JPEG
                    ImageIO.write(img, "JPEG", imgBytes);
                    Image imgNew = new Image(ImageDataFactory.create(imgBytes.toByteArray()));

                    // Replace the original image with the resized image
                    xObjects.put(imgRef, imgNew.getXObject().getPdfObject());
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            pdfDoc.close();
            return outputStream.toByteArray();
        }

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