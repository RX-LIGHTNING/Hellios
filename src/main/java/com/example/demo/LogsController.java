package com.example.demo;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LogsController {
    @FXML
    private ComboBox ActionBox;
    @FXML
    private ComboBox TableBox;
    @FXML
    private TextField UserLogin;
    @FXML
    private TableView<Log> Table;
    @FXML
    private TableColumn usercol;
    @FXML
    private TableColumn actioncol;
    @FXML
    private TableColumn tablecol;
    @FXML
    private TableColumn resultcol;
    public void initialize() {
        ActionBox.getItems().removeAll(ActionBox.getItems());
        ActionBox.getItems().addAll("DEFAULT","ADDED","DELETED", "FINISHED", "CANCELED","CHANGED");
        TableBox.getItems().removeAll(ActionBox.getItems());
        TableBox.getItems().addAll("DEFAULT","Photographs", "Orders");
        ActionBox.getSelectionModel().select("DEFAULT");
        TableBox.getSelectionModel().select("DEFAULT");
        UpdateTable(UserLogin.getText(),ActionBox.getSelectionModel().getSelectedItem().toString(),TableBox.getSelectionModel().getSelectedItem().toString());
    }
    public void UpdateTable(String userfilter, String actionfilter, String tablefilter){
        Table.getItems().clear();
        List<Log> LogList = DatabaseController.getLogs();
        usercol.setCellValueFactory(new PropertyValueFactory<>("user"));
        tablecol.setCellValueFactory(new PropertyValueFactory<>("table"));
        actioncol.setCellValueFactory(new PropertyValueFactory<>("action"));
        resultcol.setCellValueFactory(new PropertyValueFactory<>("result"));
        for (int i = 0; i < LogList.size(); i++) {
            
        }
    }
    public void FilterTable(){
        UpdateTable(UserLogin.getText(),ActionBox.getSelectionModel().getSelectedItem().toString(),TableBox.getSelectionModel().getSelectedItem().toString());
    }
}
