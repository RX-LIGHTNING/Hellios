package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignInController  {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private Label StatusLabel;
    @FXML
    private CheckBox RememberCheckBox;


        @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeySpecException {
        if (!LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty()) {

            if (DatabaseController.isLoggedIn(LoginText.getText(), PasswordText.getText())) {
                ApplicationCoreController.showMainMenu(ApplicationCoreController.st);
                ApplicationCoreController.showMainMenu(ApplicationCoreController.st);
                if(RememberCheckBox.isSelected()){
                    FileWriter writer = new FileWriter("Profile.dat", false);
                    writer.append(LoginText.getText());
                    writer.append("\n");
                    writer.append(PasswordText.getText());
                    writer.flush();
                }
            }
            else {
                LoginText.setStyle("-fx-border-color: red;");
                PasswordText.setStyle("-fx-border-color: red;");
                StatusLabel.setText("Incorrect password, check your password or login");
            }
        } else {
            LoginText.setStyle("-fx-border-color: red;");
            PasswordText.setStyle("-fx-border-color: red;");
            StatusLabel.setText("One of fields is empty");
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