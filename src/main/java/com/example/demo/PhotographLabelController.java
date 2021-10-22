package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class PhotographLabelController {
    @FXML
    private Label Description;
    @FXML
    private Label photographname;
    MainMenuController menuController;
    @FXML
    private Button commit;
    public void setData(String name, String description, MainMenuController menuController){
        this.Description.setText(description);
        this.photographname.setText(name);
        this.menuController = menuController;
    }
    public void acceptOrder() throws SQLException, IOException {
//        DatabaseController.insertOrder(photographname.getText());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("OrderConfirm.fxml"));
        Pane anchorPane = fxmlLoader.load();
        OrderConfirmController controller = fxmlLoader.getController();
        controller.setData(menuController, photographname.getText());
        menuController.UIworkspace.setCenter(anchorPane);

    }
}
