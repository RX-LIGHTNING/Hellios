package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Photo implements Initializable {

    @FXML
    private GridPane PhotoGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Photograph> PhotographList = Database.getPhotographs();
        int countY = 0;
        int countX = 0;
        int count = 0;
        try {
            for (int i = 0; i < PhotographList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PhotographLabel.fxml"));
                Pane anchorPane = fxmlLoader.load();
                PhotoGraphLabel controller = fxmlLoader.getController();
                controller.setData(PhotographList.get(i).getPhotograph(),PhotographList.get(i).getDescription());
                PhotoGrid.add(anchorPane,countX,countY);
                if (countY == 2) {
                    countY = 0;
                    countX++;
                } else {
                    countY++;
                }
            }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}

