package com.example.bookstoreapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URISyntaxException;


//
//public class BookstoreImpl extends Application {
//
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader=new   FXMLLoader(Bookstore.class.getResource("Main.fxml"));
//
//        stage.setTitle("Your digital Bookstore");
//        Scene scene=new Scene(fxmlLoader.load(), 1199, 800);
//
//        stage.setScene(scene);
//        stage.show();


   // }
    public class BookstoreImpl extends Application {
        @Override
        public void start(Stage primaryStage) throws URISyntaxException {
            MainController bookStoreUI = new MainController();
            Scene scene = new Scene(bookStoreUI.getUI(), 1000, 800);

            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            primaryStage.setTitle("Bookstore");
            primaryStage.setScene(scene);
            Image icon= new Image(getClass().getResource("log 2.jpg").toExternalForm());
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

//    public static void main(String[] args) {
//        launch();
//    }
//}

