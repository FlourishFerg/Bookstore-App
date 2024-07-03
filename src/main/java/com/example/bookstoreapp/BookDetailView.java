package com.example.bookstoreapp;


    import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
    import org.json.JSONObject;

    import java.awt.*;
    import java.io.File;
    import java.io.IOException;
    import java.net.URI;
    import java.util.UUID;

    import static com.example.bookstoreapp.Main.isPaymentSuccessful;
    import static com.example.bookstoreapp.Main.openBook;

public class BookDetailView {
        private Stage stage;
        private Book book;

        public BookDetailView(Book book) {
            this.book = book;
            stage = new Stage();
            VBox root = createDetailView();
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("details.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(book.getName());
        }

        private VBox createDetailView() {
            VBox root = new VBox(10);
            root.setPadding(new Insets(20));
            root.getStyleClass().add("detail-root");

            ImageView imageView = new ImageView(new Image("file:" + book.getImagePath()));
            imageView.setFitHeight(300);
            imageView.setFitWidth(200);
            imageView.getStyleClass().add("detail-image-view");

            Label nameLabel = new Label(book.getName());
            nameLabel.getStyleClass().add("detail-label");

            Label authorLabel = new Label("by " + book.getAuthor());
            authorLabel.getStyleClass().add("detail-label");

            Label ratingLabel = new Label("Rating: " + book.getRating());
            ratingLabel.getStyleClass().add("detail-label");

            Label priceLabel = new Label("N" + book.getPrice());
            priceLabel.getStyleClass().add("detail-label");

            Label descriptionLabel = new Label(book.getDescription());
            descriptionLabel.getStyleClass().add("detail-description-label");
            descriptionLabel.setWrapText(true);

            Button buyButton = new Button("Buy Now");
            buyButton.getStyleClass().add("button");
            buyButton.setOnAction(e -> System.out.println("Bought: " + book.getName()));
            buyButton.setOnMouseClicked(event -> {
                try {
                    handleBuyNow(book);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            root.getChildren().addAll(imageView, nameLabel, authorLabel, ratingLabel, priceLabel, descriptionLabel, buyButton);

            return root;
        }
        public void handleBuyNow(Book book) throws IOException {
        System.out.println("Buy Now button clicked for book: " + book.getName());
        String authorizationUrl = "https://checkout.paystack.com/d2xvt4b5agojdtn";
        try {
            Desktop.getDesktop().browse(new URI(authorizationUrl));
            Thread.sleep(60_000);
           openBook(book);
        } catch (Exception e) {
            e.printStackTrace();

        }
        String email = new String();
        int index =1;
            PaymentProcessor paymentProcessor = new PaymentProcessor();
            String reference = UUID.randomUUID().toString(); // this line of code is for Generating a unique reference
            String response = paymentProcessor.createCharge(email, 10000, reference); // amount is in kobo


             authorizationUrl = extractAuthorizationUrl(response);

            // Display the authorization URL to the user
            System.out.println("Please complete the payment by visiting the following URL:");
            System.out.println(authorizationUrl);
Bookstore bookstore = new Bookstore();


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


        public void show() {
            stage.show();
        }
    }

