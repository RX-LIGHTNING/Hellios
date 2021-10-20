package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignInController {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;

    @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException {
        if (!LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty()) {

            if (DatabaseController.isLoggedIn(LoginText.getText(), PasswordText.getText())) {
                ApplicationCoreController.showMainMenu(ApplicationCoreController.st);
            }
            else {
                LoginText.setStyle("-fx-border-color: red;");
                PasswordText.setStyle("-fx-border-color: red;");
            }
        } else {
            LoginText.setStyle("-fx-border-color: red;");
            PasswordText.setStyle("-fx-border-color: red;");
        };
    }

    @FXML
    protected void registrationApply() throws NoSuchAlgorithmException, IOException {
        ApplicationCoreController.showRegistrationMenu(ApplicationCoreController.st);
    }

    @FXML
    protected void exitApplication() {
        System.exit(1);
    }
}