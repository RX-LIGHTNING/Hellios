package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private PasswordField PasswordText1;
    @FXML
    private TextField Contacts;
    @FXML
    private Label StatusLabel;
    @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeySpecException {
        if(PasswordText.getText().equals(PasswordText1.getText()) && !LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty() &&!Contacts.getText().isEmpty()) {
            if(!DatabaseController.isUserExist(LoginText.getText())) {
                DatabaseController.userInsert(LoginText.getText(), PasswordText.getText(), Contacts.getText());
                ApplicationCoreController.showSignInMenu(ApplicationCoreController.st);
            }
            else{
                LoginText.setStyle("-fx-border-color: red;");
                PasswordText.setStyle("-fx-border-color: red;");
                Contacts.setStyle("-fx-border-color: red;");
                StatusLabel.setText("User with that login already exists");
            }
        }else {
            LoginText.setStyle("-fx-border-color: red;");
            PasswordText.setStyle("-fx-border-color: red;");
            PasswordText1.setStyle("-fx-border-color: red;");
            Contacts.setStyle("-fx-border-color: red;");
            StatusLabel.setText("Check inserted data");
        };
    }
    @FXML
    protected void toSignIn() throws IOException {
        ApplicationCoreController.showSignInMenu(ApplicationCoreController.st);

    }
    @FXML
    protected void exitApplication(){
        System.exit(1);
    }

}
