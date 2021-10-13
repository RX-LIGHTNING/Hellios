package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private GridPane PhotoGrid;
    @FXML
    private Label Username;
    @FXML
    private BorderPane UIworkspace;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Username.setText("Welcome back, " + User.Login);
    }

    @FXML
    protected void exitApplication() {
        System.exit(1);
    }

    @FXML
    private void setPhotographSlide() throws IOException {
        UIworkspace.setCenter(loadScene("Photo"));
    }

    @FXML
    private void signOut() throws IOException {
        HelloApplication.showSignInMenu(HelloApplication.st);
        User.clearUser();
    }

    @FXML
    private void setOptionSlide() throws IOException {
        UIworkspace.setCenter(loadScene("Options"));
    }

    @FXML
    private void setHistorySlide() throws IOException {
        UIworkspace.setCenter(loadScene("History"));
    }

    public static Parent loadScene(String scene) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource(scene + ".fxml")));
    }
}
