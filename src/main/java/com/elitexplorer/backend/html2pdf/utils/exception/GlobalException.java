package com.elitexplorer.backend.html2pdf.utils.exception;

import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {



    @ExceptionHandler(SendErrorMessageCustom.class)
    public ResponseEntity customErrorMessage(SendErrorMessageCustom exception){
        return ResponseMessage.generate("Bad Request",exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity saveErrorLog(Exception exception){
//        return ResponseMessage.generate("Bad Request",exception.getMessage(),HttpStatus.BAD_REQUEST);
//    }
}