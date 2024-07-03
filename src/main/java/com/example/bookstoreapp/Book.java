package com.example.bookstoreapp;
////
////
////import javafx.scene.layout.VBox;
////
////import java.io.File;
////
////public class Book {
////    private String name;
////    private String author;
////    private double rating;
////    private double price;
////    private String description;
////    private String imagePath;
////    private String category;
////
////    public Book(String name, String author, double rating, double price, String description, String imageUrl, String category) {
////        this.name = name;
////        this.author = author;
////        this.rating = rating;
////        this.price = price;
////        this.description = description;
////        this.imagePath = imageUrl;
////        this.category = category;
////    }
////
////    // Getters and setters
////    public String getName() {
////        return name;
////    }
////
////    public String getAuthor() {
////        return author;
////    }
////
////    public double getRating() {
////        return rating;
////    }
////
////    public double getPrice() {
////        return price;
////    }
////
////    public String getDescription() {
////        return description;
////    }
////
////    public String getImagePath() {
////        return imagePath;
////    }
////
////    public String getCategory() {
////        return category;
////    }
////
////}
//package com.example.bookstoreapp;
//
//import java.net.URI;
//
//public class Book {
//    private int id;
//    private String title;
//    private String pdfPath;
////    private URI imagePath;
//    private String imagePath;
//    private String author;
//    private double rating;
//    private double price;
//    private String description;
//    private String category;
//    private boolean purchased;
//
//    // Primary constructor
//    public Book(int id, String title, String pdfPath, String imageUrl, double price, String author, double rating, String description, String category) {
//        this.id = id;
//        this.title = title;
//        this.pdfPath = pdfPath;
//        this.imagePath = imageUrl;
//        this.price = price;
//        this.author = author;
//        this.rating = rating;
//        this.description = description;
//        this.category = category;
//        this.purchased = false;
//    }
//
//    public Book(String author) {
//        this.author = author;
//    }
//
//    public Book(String Title, String author, double rating, int price, String description, String imageUrl, String category) {
//        this.title = Title;
//        this.author = author;
//        this.rating = rating;
//        this.price = price;
//        this.description = description;
//        this.imagePath = imageUrl;
//        this.category = category;
//
//    }
//
//    public Book(int i, String title, String path, String imagePath, int i1) {
//    }
//
//    // Getters and setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getPdfPath() {
//        return pdfPath;
//    }
//
//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public double getRating() {
//        return rating;
//    }
//
//    public void setRating(double rating) {
//        this.rating = rating;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public boolean isPurchased() {
//        return purchased;
//    }
//
//    public void setPurchased(boolean purchased) {
//        this.purchased = purchased;
//    }
//
//    // Overridden toString method for easier debugging and logging
//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", pdfPath='" + pdfPath + '\'' +
//                ", imagePath='" + imagePath + '\'' +
//                ", author='" + author + '\'' +
//                ", rating=" + rating +
//                ", price=" + price +
//                ", description='" + description + '\'' +
//                ", category='" + category + '\'' +
//                ", purchased=" + purchased +
//                '}';
//    }
//
//
//
//}
import javafx.scene.layout.VBox;

import java.io.File;

public class Book{
    private String name;
    private String author;
    private double rating;
    private double price;
    private String description;
    private String imagePath;
    private String category;
    private String pdfPath;
    private boolean purchased;

    public Book(String name, String author, double rating, double price, String description, String imageUrl, String category) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.price = price;
        this.description = description;
        this.imagePath = imageUrl;
        this.category = category;
    }

    // Getters and setters
    public String getName() { return name; }

    public String getPdfPath() {
        return pdfPath;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getAuthor() { return author; }
    public double getRating() { return rating; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }
    public String getCategory() { return category; }


}