package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ApplicationCoreController extends Application {
    static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        st = stage;
        showSignInMenu(st);
    }

    public static void showSignInMenu(Stage stage) throws IOException {
        FXMLLoader SignUp = new FXMLLoader(ApplicationCoreController.class.getResource("SingIn.fxml"));
        Scene SignUpscene = new Scene(SignUp.load(), 600, 400);
        stage.setTitle("SignUp");
        stage.setScene(SignUpscene);
        stage.show();
    }

    public static void showMainMenu(Stage stage) throws IOException {
        FXMLLoader MainMenu = new FXMLLoader(ApplicationCoreController.class.getResource("MainMenu.fxml"));
        Scene MainMenuScene = new Scene(MainMenu.load(), 755, 500);
        stage.setScene(MainMenuScene);
        stage.show();
    }

    public static void showRegistrationMenu(Stage stage) throws IOException {
        FXMLLoader RegMenu = new FXMLLoader(ApplicationCoreController.class.getResource("Registration.fxml"));
        Scene RegMenuScene = new Scene(RegMenu.load(), 600, 400);
        stage.setScene(RegMenuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}