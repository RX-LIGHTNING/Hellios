package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.postgresql.PGNotification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
public class ApplicationCoreController extends Application implements Initializable {

    static Stage st;
    private static double xOffset = 0;
    private static double yOffset = 0;
    @Override
    public void start(Stage stage) throws IOException{
        stage.initStyle(StageStyle.UNDECORATED);
        st = stage;
        Scanner scanner = null;
        String login = new String();
        String password = new String();
        try {
            scanner = new Scanner(new File("Profile.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            FileWriter writer = new FileWriter("Profile.dat");
            writer.flush();
            scanner = new Scanner(new File("Profile.dat"));
        }
        if(scanner.hasNextLine()){
            login = scanner.nextLine();
            password = scanner.nextLine();

        }
        try {
            if (!login.equals("")) {
                if (DatabaseController.isLoggedIn(login, password)) {
                    showMainMenu(st);
                }
            }
            else {
                showSignInMenu(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(ApplicationCoreController.class.getResource("MainMenu.fxml")));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}