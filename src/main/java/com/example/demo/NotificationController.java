package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationController {
    @FXML
    private Label NotificationText;
    public void setData(String notify, int time, MainMenuController menuController) {
        NotificationText.setText(notify);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    menuController.NotificationBar.setCenter(null);
                });
            }
        }, time);
    }

}