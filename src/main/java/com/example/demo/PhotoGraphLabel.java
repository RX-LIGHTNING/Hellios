package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhotoGraphLabel {

    @FXML
    private Label Description;
    @FXML
    private Label photographname;
    public void setData(String name, String description){
        this.Description.setText(description);
        this.photographname.setText(name);
    }
}
