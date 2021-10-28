package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.postgresql.core.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public final class MainMenuController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private GridPane PhotoGrid;
    @FXML
    private Label Username;
    @FXML
    public BorderPane UIworkspace;
    @FXML
    private Button adminbutton;
    @FXML
    private Button adminbutton1;
    @FXML
    private Pane MainPane;
    @FXML
    public BorderPane NotificationBar;
    @FXML
    private Button adminbutton11;
    @FXML
    private ImageView BackgroundImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     adminbutton.setVisible(false);
     adminbutton1.setVisible(false);
     adminbutton11.setVisible(false);

     Username.setText("Welcome back, " + User.Login);
        try {
            UIworkspace.setCenter(loadScene("WelcomePage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(User.getStatus()){
         adminbutton.setVisible(true);
         adminbutton1.setVisible(true);
         adminbutton11.setVisible(true);
     }
    }



    @FXML
    protected void exitApplication() {
        System.exit(1);
    }

    @FXML
    public void setPhotographsListSlide() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Photo.fxml"));
        ScrollPane anchorPane = fxmlLoader.load();
        PhotographsListController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIworkspace.setCenter(anchorPane);
    }
    @FXML
    public void setWelcomePage() throws IOException {
        UIworkspace.setCenter(loadScene("WelcomePage"));
    }
    @FXML
    private void setSignOutSlide() throws IOException {
        ApplicationCoreController.showSignInMenu(ApplicationCoreController.st);
        User.clearUser();
    }

    @FXML
    private void setOptionsSlide() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Options.fxml"));
        Pane anchorPane = fxmlLoader.load();
        OptionsController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIworkspace.setCenter(anchorPane);
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
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("History.fxml"));
        Pane anchorPane = fxmlLoader.load();
        OrderHistoryController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIworkspace.setCenter(anchorPane);
    }
    public void createNotification(String notify,int time) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Notification.fxml"));
        Pane anchorPane = fxmlLoader.load();
        NotificationController controller = fxmlLoader.getController();
        controller.setData(notify, time, MainMenuController.this);
        NotificationBar.setCenter(anchorPane);
    }
    public void setUserInfo() throws IOException {
        if(User.getStatus()) {
            UIworkspace.setCenter(loadScene("UserInfo"));
        }
    }
    public void setBackgroundImage(String URL){ BackgroundImage.setImage(new Image("file:///"+URL)); }
    public void setApplicationTheme(String URL){
        MainPane.getStylesheets().add("file:///"+URL);
    }
    @FXML
    public static Parent loadScene(String scene) throws IOException {
        try {
        return FXMLLoader.load(Objects.requireNonNull(MainMenuController.class.getResource(scene + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
