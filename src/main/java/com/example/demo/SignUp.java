package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignUp {
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private PasswordField PasswordText1;

    @FXML
    protected void authorizationApply() throws NoSuchAlgorithmException, IOException, SQLException {
        if(PasswordText.getText().equals(PasswordText1.getText()) && !LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty()) {
            Database.userInsert(LoginText.getText(),PasswordText.getText());
            HelloApplication.showSignInMenu(HelloApplication.st);
        }else System.out.println("Некорректные параметры");;
    }
    @FXML
    protected void toSignIn() throws IOException {
        HelloApplication.showSignInMenu(HelloApplication.st);

    }
    @FXML
    protected void exitApplication(){
        System.exit(1);
    }

}
