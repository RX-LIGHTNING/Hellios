package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private PasswordField PasswordText1;

    @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException {
        if(PasswordText.getText().equals(PasswordText1.getText()) && !LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty()) {
            DatabaseController.userInsert(LoginText.getText(),PasswordText.getText());
            ApplicationCoreController.showSignInMenu(ApplicationCoreController.st);
        }else System.out.println("Некорректные параметры");;
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
