package com.example.bookstoreapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private List<Book> books;

    public Bookstore() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBooksFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    String title = file.getName().replaceFirst("[.][^.]+$", "");
                    String imagePath = extractImage(file);
//        Book book = new Book();
////        1, title, file.getPath(), imagePath, 100000);
//        //   (1,title, file.getPath(),imagePath,100000,);
//        books.add(book);

                }
            }
        }
    }

    private String extractImage(File file) {
        String outputImagePath = "covers/" + file.getName().replaceFirst("[.][^.]+$", "") + "_cover.jpg";
        new File("covers").mkdir(); // Create directory for covers if it doesn't exist
        try {
            PDDocument document = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ImageIO.write(image, "JPEG", new File(outputImagePath));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            outputImagePath = null;
        }
        return outputImagePath;
    }


public boolean purchaseBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book book = books.get(index);
            if (!book.isPurchased()) {
                book.setPurchased(true);
                return true;
            }
        }
        return false;
    }
}