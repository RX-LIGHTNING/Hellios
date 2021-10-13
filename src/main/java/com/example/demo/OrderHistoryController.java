package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Order> OrderList = DatabaseController.getOrders();
        Photograph.setCellValueFactory(new PropertyValueFactory<>("Photograph"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        for (int i = 0; i < OrderList.size(); i++) {
            Table.getItems().add(OrderList.get(i));
        }
    }

}

