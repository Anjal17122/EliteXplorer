package com.elitexplorer.backend.html2pdf.utils;

import java.io.File;
import java.nio.file.Paths;

public class Constants {
    public static final String imagePath = "C:\\Users\\Dell\\Desktop\\images";
    public static final String fontPath = Paths.get("")
            .toAbsolutePath()
            .toString() +File.separator + "backend"+File.separator + "src" +File.separator +"main" +File.separator +"resources" +File.separator +"static" +File.separator +"fonts";

    public static final String imageCopySource = "C:\\Users\\Dell\\Desktop\\backend\\backend\\src\\main\\resources\\static\\uploads";

    public static final String imageCopyDest = "C:\\Users\\Dell\\Desktop\\images";


//    public static final String imagePath = "/Users/username/Desktop/images;
//    public static final String fontPath = "/Users/username/Desktop/backend/backend/src/main/resources/static/fonts";


}
