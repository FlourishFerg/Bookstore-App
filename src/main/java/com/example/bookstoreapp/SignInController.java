package com.example.bookstoreapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private RadioButton rb_bookstore;
    @FXML
    private RadioButton rb_others;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup= new ToggleGroup();
        rb_bookstore.setToggleGroup(toggleGroup);
        rb_others.setToggleGroup(toggleGroup);

        rb_bookstore.setSelected(true);

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName= ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!Username.getText().trim().isEmpty()&& !Password.getText().trim().isEmpty()){
                    UserUtility.SignUpUser(event,Username.getText(), Password.getText());
                }else {
                    System.out.println("Please fill in all information");
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });
button_login.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        UserUtility.changeScene(event,"LogIn.fxml","Log in",null);
    }
});
    }
}
