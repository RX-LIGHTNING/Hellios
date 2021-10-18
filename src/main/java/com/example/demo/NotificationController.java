package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationController {
    @FXML
    private Label NotificationText;
    @FXML
    private Label NotificationPane;
    public void setData(String notify, int time) {
        NotificationText.setText(notify);
        Timer timer1 = new Timer();

        TimerTask Task1 = new TimerTask() {
            @Override
            public void run() {
                NotificationText=null;
            }
        };
        timer1.schedule(Task1,100);
    }

}
