package com.elitexplorer.backend.email.service;

import com.elitexplorer.backend.email.model.EmailDto;
import com.itextpdf.text.DocumentException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface EmailInterface {

    void sendEmail(EmailDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, DocumentException;


}
