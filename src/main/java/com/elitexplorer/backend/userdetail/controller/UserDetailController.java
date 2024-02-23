package com.elitexplorer.backend.userdetail.controller;


import com.elitexplorer.backend.html2pdf.utils.DtoConvert;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.service.UserDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDetailController {


    @Autowired
    UserDetailInterface service;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserDetailDto dto){
        return ResponseMessage.success(DtoConvert.convert(service.saveUser(dto)));
    }

    @GetMapping("/change/status")
    public ResponseEntity changeStatus(@RequestParam("userId") int userId, @RequestParam("status")UserStatus status){
        return ResponseMessage.success(DtoConvert.convert(service.changeStatus(userId, status)));
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseMessage.success(service.getAll().stream().map(DtoConvert::convert).collect(Collectors.toList()));
    }


}
