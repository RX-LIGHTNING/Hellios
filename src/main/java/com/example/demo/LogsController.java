package com.example.demo;

import javafx.collections.ObservableList;
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
import java.util.function.Predicate;

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
        ActionBox.getItems().addAll("","ADDED","DELETED", "FINISHED", "CANCELED","CHANGED");
        TableBox.getItems().removeAll(ActionBox.getItems());
        TableBox.getItems().addAll("","Photographs", "Orders");
        ActionBox.getSelectionModel().select("");
        TableBox.getSelectionModel().select("");
        UpdateTable(UserLogin.getText(),ActionBox.getSelectionModel().getSelectedItem().toString(),TableBox.getSelectionModel().getSelectedItem().toString());
    }
    public void UpdateTable(String userfilter, String actionfilter, String tablefilter){
        Table.getItems().clear();
        List<Log> LogList = DatabaseController.getLogs();
        usercol.setCellValueFactory(new PropertyValueFactory<>("user"));
        tablecol.setCellValueFactory(new PropertyValueFactory<>("table"));
        actioncol.setCellValueFactory(new PropertyValueFactory<>("action"));
        resultcol.setCellValueFactory(new PropertyValueFactory<>("result"));
        boolean temp2 = false;
        for (int i = 0; i < LogList.size(); i++) {
            if(userfilter == "" && actionfilter =="" && tablefilter == "") {
                Table.getItems().add(LogList.get(i));
            }
            else{
                temp2 = false;
                if (Objects.equals(userfilter, LogList.get(i).getUser()) && userfilter != ""){
                    temp2 = true;
                }
                else if (userfilter != ""){
                    temp2 = false;
                }
                if (Objects.equals(actionfilter, LogList.get(i).getAction()) && actionfilter != "") {
                    temp2 = true;
                }
                else if (actionfilter != ""){
                    temp2 = false;
                }
                if (Objects.equals(tablefilter, LogList.get(i).getTable())&& tablefilter != "") {
                    temp2 = true;
                }
                else if (tablefilter != ""){
                    temp2 = false;
                }
                if (Objects.equals(tablefilter, LogList.get(i).getTable())&& tablefilter != "" && Objects.equals(actionfilter, LogList.get(i).getAction()) && actionfilter != "" && LogList.get(i).getUser().contains(userfilter)) {
                    temp2 = true;
                }
                else if (tablefilter != "" && actionfilter != ""){
                    temp2 = false;
                }

                if(temp2){Table.getItems().add(LogList.get(i));}
            }
        }
    }
    public void FilterTable(){
        UpdateTable(UserLogin.getText(),ActionBox.getSelectionModel().getSelectedItem().toString(),TableBox.getSelectionModel().getSelectedItem().toString());
    }
}
