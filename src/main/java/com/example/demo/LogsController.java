package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Objects;

public class LogsController {
    @FXML
    private ComboBox ActionBox;
    @FXML
    private ComboBox TableBox;
    @FXML
    private TextField UserLogin;
    @FXML
    public void initialize() {
        ActionBox.getItems().removeAll(ActionBox.getItems());
        ActionBox.getItems().addAll("ADDED", "FINISHED", "CANCELED","CHANGED", "CREATED");
        TableBox.getItems().removeAll(ActionBox.getItems());
        TableBox.getItems().addAll("Photographs", "Orders");
    }
    public void UpdateTable(){


    }
}
