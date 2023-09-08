package com.elitexplorer.backend.email.controller;

import com.elitexplorer.backend.email.model.EmailDto;
import com.elitexplorer.backend.email.service.EmailInterface;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/email")
public class EmailController {


    @Autowired
    EmailInterface service;


    @PostMapping
    public ResponseEntity sendEmail(@RequestBody EmailDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        service.sendEmail(dto,request,response);
        return ResponseMessage.success(true, "Email Sent Successfully");
    }
}
