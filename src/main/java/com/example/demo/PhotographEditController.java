package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PhotographEditController implements Initializable {
    @FXML
    private TextField PhotographName;
    @FXML
    private TextField PhotographDescription;
    @FXML
    private TableColumn PhotographColumn;
    @FXML
    private TableColumn DescriptionColumn;
    @FXML
    private TableView<Photograph> PhotographTable;
    public void addPhotograph(){

        if(!PhotographName.getText().isEmpty()&&!PhotographDescription.getText().isEmpty()&&User.getStatus()) {
            DatabaseController.insertPhotograph(PhotographName.getText(), PhotographDescription.getText());
            updateTable();
        }
    }
    public void showPhotographInfo(){
        Photograph temp = PhotographTable.getSelectionModel().getSelectedItem();
        PhotographName.setText(temp.getPhotograph());
        PhotographDescription.setText(temp.getDescription());
    }
    public void updatePhotographInfo(){
        Photograph temp = PhotographTable.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(temp)) {
            DatabaseController.updatePhotograph(temp.getId(), PhotographName.getText(), PhotographDescription.getText());
            updateTable();
        }
    }
    public void deletePhotograph(){
        Photograph temp = PhotographTable.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(temp)) {
        DatabaseController.deletePhotograph(temp.getId());
            updateTable();
        }
    }
    public void updateTable(){
        PhotographTable.getItems().clear();
        List<Photograph> OrderList = DatabaseController.getPhotographs();
        PhotographColumn.setCellValueFactory(new PropertyValueFactory<>("photograph"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        for (int i = 0; i < OrderList.size(); i++) {
            PhotographTable.getItems().add(OrderList.get(i));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }
}
