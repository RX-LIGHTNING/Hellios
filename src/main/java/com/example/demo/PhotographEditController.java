package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
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
        }
    }
    public void showPhotographInfo(){
        Photograph temp = PhotographTable.getSelectionModel().getSelectedItem();
        PhotographName.setText(temp.getPhotograph());
        PhotographDescription.setText(temp.getDescription());
    }
    public void updatePhotographInfo(){
        Photograph temp = PhotographTable.getSelectionModel().getSelectedItem();
        DatabaseController.updatePhotograph(temp.getId(),PhotographName.getText(),PhotographDescription.getText());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Photograph> OrderList = DatabaseController.getPhotographs();
        PhotographColumn.setCellValueFactory(new PropertyValueFactory<>("photograph"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        for (int i = 0; i < OrderList.size(); i++) {
            PhotographTable.getItems().add(OrderList.get(i));
        }
    }
}
