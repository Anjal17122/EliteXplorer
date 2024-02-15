package com.elitexplorer.backend.file.controller;



import com.elitexplorer.backend.html2pdf.utils.Constants;
import com.elitexplorer.backend.html2pdf.utils.ResponseMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {



    private String Path = Constants.imagePath + File.separator + "images";
    @GetMapping(value = "images/{fileName}.{ext}"/*, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.APPLICATION_PDF_VALUE}*/, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<ByteArrayResource> getImage( @PathVariable("fileName") String fileName,
                                    @PathVariable("ext") String ext) throws IOException {
        InputStream in = new FileInputStream(
                new File(String.format("%s/%s.%s", Path, fileName, ext)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = in.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok().body(resource);
    }

    @RequestMapping(value = "images/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveImage(@RequestParam("file") MultipartFile files) {

        System.out.println(" File Size : " + files.getSize());
        String imageName = "";
        try {
            imageName = uploadImage(files);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseMessage.success(imageName);
    }









    public String uploadImage(MultipartFile multipartFile) throws IOException {
        // String realPath = context.getRealPath("/");
        // System.out.println("-------------------------Initial Path : " + initialPath);

        int count = 0;
        String randomNumber = "";
        String uploadTempPath = Path + File.separator;

        String fileName = new Date().getTime() + "_" + multipartFile.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.indexOf(".") + 1);
        System.out.println("Success ---------");
        File dir = new File(uploadTempPath);
        if (!dir.exists()) {
            System.out.println("realPath => " + uploadTempPath);
            dir.mkdirs();
        }

        do {
            String uploadTempPaths = uploadTempPath + fileName;
            File uploadTempDir = new File(uploadTempPaths);
            if (!uploadTempDir.exists()) {
                break;
            }

//            if (count++ > 1000) {
//                throw new SendErrorMessageCustom("Too large number of image exists in the Directory.");
//            }

        } while (true);

        // convert to jpg start
        if (fileExtension.equals("png")) {
            InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
            BufferedImage bufferedImage = ImageIO.read(in);
            // create a blank, RGB, same width and height, and a white background
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            // write to jpeg file
            ImageIO.write(newBufferedImage, fileExtension,
                    new File(uploadTempPath + fileName));
            // convert to jpg end
        } else {
            // Now do something with file...
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadTempPath + fileName));
        }

        return fileName;
    }





}
