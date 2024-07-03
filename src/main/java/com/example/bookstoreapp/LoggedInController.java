package com.example.bookstoreapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button  getStarted;
    @FXML
    private Label labelWelcome;
    @FXML
    private Label labelFav;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
getStarted.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
UserUtility.changeScene(event,"LogIn.fxml","Log in",null);
    }
});
    }
    public void setUserInformation(String Username, String FavBookstore){
        labelWelcome.setText("Welcome "+ Username+"!");
        labelFav.setText("Your Favourite Bookstore is ready;");
    }
}
