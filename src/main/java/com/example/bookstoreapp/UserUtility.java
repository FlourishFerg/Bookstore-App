
package com.example.bookstoreapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class UserUtility {

    public static void SignUpUser(ActionEvent event, String username, String password) {
        boolean signUpSuccess = jdbc.signUp(username, password);
        if (signUpSuccess) {
            changeScene(event, "Main.fxml", "Bookstore", username);
        } else {
            System.out.println("Sign up failed!");
            showAlert("Sign up failed! Please try again.");
        }
    }

    public static boolean LogInUser(ActionEvent event, String username, String password) {
        boolean signInSuccess = jdbc.signIn(username, password);
        if (signInSuccess) {
            changeScene(event, "Main.fxml", "Bookstore", username);
        } else {
            System.out.println("Invalid credentials!");
            showAlert("Invalid username or password!");
        }
        return signInSuccess;
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UserUtility.class.getResource(fxmlFile));
                root = loader.load();
                MainController mainController = loader.getController();
               // mainController.setUserInformation(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(UserUtility.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
