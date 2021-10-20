package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationController {
    @FXML
    private Label NotificationText;
    @FXML
    private Label NotificationPane;
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
