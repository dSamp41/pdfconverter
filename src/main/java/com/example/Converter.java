package com.example;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Converter {
    public void convert(String filePath){
        File f = new File(filePath);
        try (PDDocument doc = Loader.loadPDF(f)) {
            PDFRenderer renderer = new PDFRenderer(doc);

            System.out.println("File name: " + f.getName().replace(".pdf", ""));
            String newDir = f.getName().replace(".pdf", "");
            File outputDir = new File(newDir);

            if (!outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    System.err.println("Failed to create parent directories.");
                    return;
                }
            }

            int nPages = doc.getNumberOfPages();

            for(int i=0; i<nPages; i++){
                BufferedImage page = renderer.renderImage(i);
                
                File outputFile = new File(outputDir, "/page_" + (i+1) + ".png");
                ImageIO.write(page, "png", outputFile);

                System.out.printf("Completed page %d/%d\n", i, nPages);
            }
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
