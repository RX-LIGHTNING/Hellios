package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    @FXML
    private TableView<Order> AdminTable;
    @FXML
    private TableColumn Photograph;
    @FXML
    private TableColumn Status;
    @FXML
    private TableColumn UserId;
    @FXML
    private TextField PhotographName;
    @FXML
    private TextField PhotographDescription;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }
    public void updateTable(){
        List<Order> OrderList = DatabaseController.getAdminOrders();
        Photograph.setCellValueFactory(new PropertyValueFactory<>("Photograph"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        UserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        for (int i = 0; i < OrderList.size(); i++) {
            AdminTable.getItems().add(OrderList.get(i));
        }
    }
    public void finishOrder(){
        Order temp = AdminTable.getSelectionModel().getSelectedItem();
        DatabaseController.updateOrder(temp.getId());
        AdminTable = new TableView<Order>();
        updateTable();
    }
    public void addPhotograph(){
        if(!PhotographName.getText().isEmpty()&&!PhotographDescription.getText().isEmpty()) {
            DatabaseController.insertPhotograph(PhotographName.getText(), PhotographDescription.getText());
        }
    }
}
