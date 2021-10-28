package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ApplicationCoreController extends Application {
    static Stage st;
    private static double xOffset = 0;
    private static double yOffset = 0;
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        st = stage;
        showSignInMenu(st);
    }

    public static void showSignInMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ApplicationCoreController.class.getResource("SingIn.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void showMainMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ApplicationCoreController.class.getResource("MainMenu.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void showRegistrationMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ApplicationCoreController.class.getResource("Registration.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}