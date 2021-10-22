package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        DatabaseController.insertOrder(photographname.getText());
        menuController.createNotification("Your order has been created", 3000);
    }
}
