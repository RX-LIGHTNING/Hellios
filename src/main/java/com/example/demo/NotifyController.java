package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotifyController {
    @FXML
    private Label Text;
    private int time;
    public void setData(String type, int duration){
        this.Text.setText(type);
        this.time = duration;
    }
}
