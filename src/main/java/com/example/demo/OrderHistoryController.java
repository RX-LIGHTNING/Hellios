package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {
    @FXML
    private TableView<Order> Table;
    @FXML
    private TableColumn Photograph;
    @FXML
    private TableColumn Status;
    @FXML
    private TextField FilterField;
    @FXML
    private CheckBox FilterCheck;
    @FXML
    private MainMenuController ParentController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable("", false);
    }
    public void setData(MainMenuController menuController){
        ParentController = menuController;
    }
    public void updateTable(String filter, Boolean checkbox){
        Table.getItems().clear();
        List<Order> OrderList = DatabaseController.getOrders();
        Photograph.setCellValueFactory(new PropertyValueFactory<>("Photograph"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        for (int i = 0; i < OrderList.size(); i++) {

            if (filter!=""&& OrderList.get(i).getPhotograph().equals(filter)) {
                if(checkbox == true && OrderList.get(i).getStatus().equals("In progress."))
                Table.getItems().add(OrderList.get(i));
                else if (checkbox == false){
                    Table.getItems().add(OrderList.get(i));
                }
            }
            else if (filter==""){
                if(checkbox == true && OrderList.get(i).getStatus().equals("In progress."))
                    Table.getItems().add(OrderList.get(i));
                else if (checkbox == false){
                    Table.getItems().add(OrderList.get(i));
                }
            }
        }

    }
    public void filterTable() throws IOException {
        updateTable(FilterField.getText(), FilterCheck.isSelected());
        ParentController.createNotification("Table is Filtered",3000);
    }
}

