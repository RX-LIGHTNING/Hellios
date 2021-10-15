package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public final class MainMenuController implements Initializable {
    @FXML
    private GridPane PhotoGrid;
    @FXML
    private Label Username;
    @FXML
    private BorderPane UIworkspace;
    @FXML
    private Button adminbutton;
    @FXML
    private Button adminbutton1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     adminbutton.setVisible(false);
     adminbutton1.setVisible(false);
     if(User.getStatus()){
        adminbutton.setVisible(true);
         adminbutton1.setVisible(true);
     }
        Username.setText("Welcome back, " + User.Login);
    }

    @FXML
    protected void exitApplication() {
        System.exit(1);
    }

    @FXML
    private void setPhotographsListSlide() throws IOException {
        UIworkspace.setCenter(loadScene("Photo"));
    }

    @FXML
    private void setSignOutSlide() throws IOException {
        ApplicationCoreController.showSignInMenu(ApplicationCoreController.st);
        User.clearUser();
    }

    @FXML
    private void setOptionsSlide() throws IOException {
        UIworkspace.setCenter(loadScene("Options"));
    }
    @FXML
    private void setAdminPanel() throws IOException {
      if(User.getStatus()) {
            UIworkspace.setCenter(loadScene("Admin"));
      }
    }
    @FXML
    private void setPhotographControlSlide() throws IOException {
        if(User.getStatus()) {
            UIworkspace.setCenter(loadScene("PhotographControl"));
        }
    }
    @FXML
    private void setOrderHistorySlide() throws IOException {
        UIworkspace.setCenter(loadScene("History"));
    }

    @FXML
    public static Parent loadScene(String scene) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(MainMenuController.class.getResource(scene + ".fxml")));
    }
}
