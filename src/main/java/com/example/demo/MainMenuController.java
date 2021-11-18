package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.layout.VBox;
import org.postgresql.core.Notification;

import java.io.*;
import java.net.URL;
import java.util.*;

public final class MainMenuController implements Initializable {
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
    @FXML
    private VBox NavBarBox;
    @FXML
    private Button mainmenubutton1;
    @FXML
    private Button mainmenubutton2;
    @FXML
    private Button mainmenubutton3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Scanner scanner = new Scanner(new File("Options.dat"));
            if(scanner.hasNextLine()){
                setBackgroundImage(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Username.setText("Welcome back, " + User.Login);
        try {
            UIworkspace.setCenter(loadScene("WelcomePage"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!User.getStatus()){
            NavBarBox.getChildren().remove(adminbutton);
            NavBarBox.getChildren().remove(adminbutton1);
            NavBarBox.getChildren().remove(adminbutton11);
     }
        else {
            NavBarBox.getChildren().remove(mainmenubutton1);
            NavBarBox.getChildren().remove(mainmenubutton2);
            NavBarBox.getChildren().remove(mainmenubutton3);
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
        Pane anchorPane = fxmlLoader.load();
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
        FileWriter fileWriter = new FileWriter(new File("Profile.dat"),false);
        fileWriter.append("");
        fileWriter.flush();
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("admin.fxml"));
            Pane anchorPane = fxmlLoader.load();
            AdminPanelController controller = fxmlLoader.getController();
            controller.setData(MainMenuController.this);
            UIworkspace.setCenter(anchorPane);
        }
    }
    @FXML
    private void setPhotographControlSlide() throws IOException {
        if(User.getStatus()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PhotographControl.fxml"));
            Pane anchorPane = fxmlLoader.load();
            PhotographEditController controller = fxmlLoader.getController();
            controller.setData(MainMenuController.this);
            UIworkspace.setCenter(anchorPane);
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
    public void setLogs() throws IOException {
        if(User.getStatus()) {
            UIworkspace.setCenter(loadScene("GetLogs"));
        }
    }
    public void setBackgroundImage(String URL){
        if(URL!="") {
            BackgroundImage.setImage(new Image("file:///" + URL));
        }
        else if(URL ==""){
            BackgroundImage.setImage(null);
        }
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
