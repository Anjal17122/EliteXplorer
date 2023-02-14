package com.elitexplorer.backend.file.controller;

import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.pdf1.model.Pdf1;
import com.elitexplorer.backend.pdf1.model.dto.Pdf1Dto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

//,consumes = {"multipart/form-data"}
    @GetMapping("/uploads/{fileName}.{ext}")
    public @ResponseBody
    ResponseEntity<ByteArrayResource> getImage(@PathVariable("fileName") String fileName,
                                               @PathVariable("ext") String ext) throws IOException {
        InputStream in = new FileInputStream(
                new File(String.format("%s/%s.%s",Constants.imagePath, fileName, ext)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = in.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok().body(resource);
        //return IOUtils.toByteArray(in);
    }
}
