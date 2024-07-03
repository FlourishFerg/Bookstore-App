package com.example.bookstoreapp;


    import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class OpenPurBook {
        public static void main(String[] args) {
            // Simulate payment and purchase
            String purchasedBook = "MyBook.pdf";
            openPDF(purchasedBook);
        }

        public static void openPDF(String pdfFileName) {
            // Directory where your PDF files are stored
            String directoryPath = "C:/Users/Princess/Downloads/MyBooks";

            // Create a File object for the PDF file
            File pdfFile = new File(directoryPath + pdfFileName);

            // Check if the file exists
            if (pdfFile.exists()) {
                try {
                    // Open the PDF file using the default PDF viewer
                    Desktop.getDesktop().open(pdfFile);
                    System.out.println("PDF file opened successfully.");
                } catch (IOException e) {
                    System.err.println("Error opening PDF file: " + e.getMessage());
                }
            } else {
                System.out.println("The specified PDF file does not exist.");
            }
        }
    }

