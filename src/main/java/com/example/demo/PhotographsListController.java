package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PhotographsListController  {

    @FXML
    private GridPane PhotoGrid;
    MainMenuController menuController;
    public void setData(MainMenuController menuController){
        this.menuController = menuController;
        List<Photograph> PhotographList = DatabaseController.getPhotographs();
        int countY = 0;
        int countX = 0;
        try {
            for (Photograph photograph : PhotographList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PhotographLabel.fxml"));
                Pane anchorPane = fxmlLoader.load();
                PhotographLabelController controller = fxmlLoader.getController();
                controller.setData(photograph.getPhotograph(), photograph.getDescription(),menuController);
                PhotoGrid.add(anchorPane, countX, countY);
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

