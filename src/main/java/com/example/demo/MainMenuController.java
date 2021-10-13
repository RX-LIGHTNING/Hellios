package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private GridPane PhotoGrid;
    @FXML
    private Label Username;
    @FXML
    private BorderPane UIworkspace;
    @FXML
    private BorderPane NotificationBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void setOrderHistorySlide() throws IOException {
        UIworkspace.setCenter(loadScene("History"));
    }
    @FXML
    private void createNotification() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Notify.fxml"));
        Pane anchorPane = fxmlLoader.load();
        NotifyController controller = fxmlLoader.getController();
        controller.setData("Запись была успешно добавлена",10);
        NotificationBar.setCenter(anchorPane);
    }
    public static Parent loadScene(String scene) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(MainMenuController.class.getResource(scene + ".fxml")));
    }
}
