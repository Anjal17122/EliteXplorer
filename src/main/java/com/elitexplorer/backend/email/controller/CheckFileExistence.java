package com.elitexplorer.backend.email.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class CheckFileExistence {
    public static void main(String[] args) {
        String replasedBody = "anjal\nsapkota".replaceAll("\\\\", "<br/>");
    }
//    public static void main(String[] args) throws IOException {
        // Specify the file path relative to the "static" folder
//        String filePathInStaticFolder = "solo.png"; // Replace with the actual file path
//
//        // Create a Resource object for the file
//        File file = new ClassPathResource("static/images/" + filePathInStaticFolder).getFile();
//
//        try {
//            // Get the file's absolute path
//            String absoluteFilePath = resource.getFile().getAbsolutePath();
//            System.out.println("Absolute File Path: " + absoluteFilePath);
//        } catch (Exception e) {
//            // Handle any exceptions that may occur (e.g., file not found)
//            e.printStackTrace();
//        }
//    }
}
