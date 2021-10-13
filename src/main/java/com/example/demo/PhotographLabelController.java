package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class PhotographLabelController {
    @FXML
    private Label Description;
    @FXML
    private Label photographname;
    @FXML
    private Button commit;
    public void setData(String name, String description){
        this.Description.setText(description);
        this.photographname.setText(name);
    }
    public void acceptOrder() throws SQLException {
        DatabaseController.insertOrder(photographname.getText());
    }
}
