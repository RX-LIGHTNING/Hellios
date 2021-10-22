package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

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
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i<PhotographList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PhotographLabel.fxml"));
                Pane anchorPane = fxmlLoader.load();

                PhotographLabelController controller = fxmlLoader.getController();
                controller.setData(PhotographList.get(i).getPhotograph(), PhotographList.get(i).getDescription(),menuController);
                if(column==2){
                    column=0;
                    row++;
                }
                PhotoGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                PhotoGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                PhotoGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //
                PhotoGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                PhotoGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                PhotoGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                //
                PhotoGrid.add(anchorPane, column++, row);


                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

