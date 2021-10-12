package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignIn {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;

    @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException {
        if(!LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty()) {

            if( Database.isLoggedIn(LoginText.getText(),PasswordText.getText())) {
                HelloApplication.showMainMenu(HelloApplication.st);
            }
        }else System.out.println("Некорректные параметры");
    }
    @FXML
    protected void registrationApply() throws NoSuchAlgorithmException, IOException {
        HelloApplication.showRegistrationMenu(HelloApplication.st);
    }
    @FXML
    protected void exitApplication(){
        System.exit(1);
    }
}