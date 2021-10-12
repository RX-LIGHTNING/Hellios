package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class history implements Initializable {
    @FXML
    private TableView<Order> Table;
    @FXML
    private TableColumn Photograph;
    @FXML
    private TableColumn Status;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet OrderList=Database.getOrders();
        Photograph.setCellValueFactory(new PropertyValueFactory<>("Photograph"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            try {
                while (OrderList.next()){
                Order temp = new Order(OrderList.getString("photograph"),OrderList.getBoolean("status"));
                    Table.getItems().add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

