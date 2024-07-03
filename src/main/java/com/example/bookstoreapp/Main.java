package com.example.bookstoreapp;

import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Bookstore bookstore = new Bookstore();
        bookstore.addBooksFromDirectory("C:/Users/Princess/Downloads/MyBooks");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayBooks(bookstore);
                    break;
                case 2:
                    try {
                        purchaseBook(scanner, bookstore);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for visiting the Bookstore. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private static void displayMenu() {
        System.out.println("Welcome to the Bookstore!");
        System.out.println("1. Display available books");
        System.out.println("2. Purchase a book");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void displayBooks(Bookstore bookstore) {
        System.out.println("Available Books:");
        int index = 1;
        for (Book book : bookstore.getBooks()) {
            System.out.println(index++ + ". " + book.getName());
        }
    }

    private static void purchaseBook(Scanner scanner, Bookstore bookstore) throws IOException {
        displayBooks(bookstore);
        System.out.print("Enter the index of the book you want to purchase: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        PaymentProcessor paymentProcessor = new PaymentProcessor();
        String reference = UUID.randomUUID().toString(); // this line of code is for Generating a unique reference
        String response = paymentProcessor.createCharge(email, 10000, reference); // amount is in kobo


        String authorizationUrl = extractAuthorizationUrl(response);

        // Display the authorization URL to the user
        System.out.println("Please complete the payment by visiting the following URL:");
        System.out.println(authorizationUrl);

        System.out.print("Press Enter after completing the payment...");
        scanner.nextLine(); // Wait for the user to complete the payment

        // Verify the payment
        String verificationResponse = paymentProcessor.verifyPayment(reference);
        if (isPaymentSuccessful(verificationResponse)) {
            boolean purchaseSuccess = bookstore.purchaseBook(index - 1);
            if (purchaseSuccess) {
                System.out.println("Book purchased successfully!");
//            } else {
//                System.out.println("Failed to purchase the book.");
//            }
//        } else {
//            System.out.println("Payment verification failed. Please try again.");
//        }
//    }
                Book purchasedBook = bookstore.getBooks().get(index - 1);
                // Open the purchased book
                openBook(purchasedBook);
            } else {
                System.out.println("Failed to purchase the book.");
            }
        } else {
            System.out.println("Payment verification failed. Please try again.");
        }
    }

    public static void openBook(Book book) {
        try {
            // Assuming the PDF file path is stored in the pdfPath variable of the Book class
            String pdfPath = book.getPdfPath();
            File pdfFile = new File(pdfPath);

            // Check if the file exists
            if (pdfFile.exists()) {
                // Open the PDF file using the default PDF viewer
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("The specified PDF file does not exist.");
            }
        } catch (IOException e) {
            System.err.println("Error opening PDF file: " + e.getMessage());
        }
    }
    private static String extractAuthorizationUrl(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getJSONObject("data").getString("authorization_url");
    }

    public static boolean isPaymentSuccessful(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getBoolean("status");
    }

}
