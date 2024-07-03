//package com.example.bookstoreapp;
//    import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//    public class PdfImageExtractor {
//
//        public static void main(String[] args) {
//            try {
//                String pdfFilePath = "C:/Users/flour/Downloads/MyBooks";
//                String outputImagePath = "C:/Users/flour/Downloads/MyBooksImg";
//
//                PDDocument document = PDDocument.load(new File(pdfFilePath));
//                PDFRenderer pdfRenderer = new PDFRenderer(document);
//                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
//
//                ImageIO.write(image, "JPEG", new File(outputImagePath));
//                document.close();
//
//                System.out.println("Cover image extracted to: " + outputImagePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
