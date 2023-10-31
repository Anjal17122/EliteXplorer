package com.elitexplorer.backend;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import java.awt.Color;
public class test {
    public static float FACTOR = 1.0f;
    public static void main(String[] args) throws DocumentException, IOException {
         String source = "C:\\Users\\Dell\\Downloads\\23.pdf";
        String dest = "C:\\Users\\Dell\\Downloads\\compressed.pdf";
//        manipulatePdf(source,dest);
compressPdf(source,dest);
    }


    public static void compressPdf(String source, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(new FileInputStream(source));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        int total = reader.getNumberOfPages() + 1;
        for ( int i=1; i<total; i++) {
            reader.setPageContent(i + 1, reader.getPageContent(i + 1));
        }
        stamper.setFullCompression();
        stamper.close();
    }

    public static  void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfName key = new PdfName("ITXT_SpecialId");
        PdfName value = new PdfName("123456789");
        // Read the file
        PdfReader reader = new PdfReader(src);
        int n = reader.getXrefSize();
        PdfObject object;
        PRStream stream;
        // Look for image and manipulate image stream
        for (int i = 0; i < n; i++) {
            object = reader.getPdfObject(i);
            if (object == null || !object.isStream())
                continue;
            stream = (PRStream)object;
            // if (value.equals(stream.get(key))) {
            PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
            System.out.println(stream.type());
            if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
                PdfImageObject image = new PdfImageObject(stream);
                BufferedImage bi = image.getBufferedImage();
                if (bi == null) continue;
                int width = (int)(bi.getWidth() * FACTOR);
                int height = (int)(bi.getHeight() * FACTOR);
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
                Graphics2D g = img.createGraphics();

                g.setBackground(new Color(0, 0, 0, 0)); // Transparent black
                g.clearRect(0, 0, width, height);

                g.drawRenderedImage(bi, at);
                ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
                ImageIO.write(img, "JPG", imgBytes);
                stream.clear();
                stream.setData(imgBytes.toByteArray(), false, PRStream.DEFAULT_COMPRESSION);
                stream.put(PdfName.TYPE, PdfName.XOBJECT);
                stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
                stream.put(key, value);
                stream.put(PdfName.FILTER, PdfName.DCTDECODE);
                stream.put(PdfName.WIDTH, new PdfNumber(width));
                stream.put(PdfName.HEIGHT, new PdfNumber(height));
                stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
                stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);
            }
        }
        // Save altered PDF
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }


}
