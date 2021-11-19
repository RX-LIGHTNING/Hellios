package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
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
    private TableColumn userName;
    @FXML
    private TableColumn userContact;
    @FXML
    private TableColumn orderDate;
    @FXML
    private TextField PhotographName;
    @FXML
    private TextField FilterField;
    @FXML
    private TextField PhotographDescription;
    private MainMenuController ParentController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable("");
    }
    public void setData(MainMenuController parentController){
        this.ParentController = parentController;
    }
    public void updateTable(String filter){

            AdminTable.getItems().clear();
            List<Order> OrderList = DatabaseController.getAdminOrders();
            Photograph.setCellValueFactory(new PropertyValueFactory<>("Photograph"));
            Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            UserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
            userName.setCellValueFactory(new PropertyValueFactory<>("username"));
            userContact.setCellValueFactory(new PropertyValueFactory<>("usercontact"));
            orderDate.setCellValueFactory(new PropertyValueFactory<>("orderdate"));

            for (int i = 0; i < OrderList.size(); i++) {
                if (filter != "" && OrderList.get(i).getPhotograph().contains(filter)) {
                    AdminTable.getItems().add(OrderList.get(i));
                } else if (filter == "") {
                    AdminTable.getItems().add(OrderList.get(i));
                }
            }

    }
    public void filterTable() throws IOException {
        ParentController.createNotification("Table has been filtered", 3000);
        updateTable(FilterField.getText());
    }
    public void finishOrder() throws IOException {

        Order temp = AdminTable.getSelectionModel().getSelectedItem();
        if(temp.getStatus()=="In progress.") {
            DatabaseController.updateOrder(temp.getId(), 2);
            updateTable(FilterField.getText());
            ParentController.createNotification("Order has been finished", 3000);
        }
    }

    public void InprogressOrder() throws IOException {
        Order temp = AdminTable.getSelectionModel().getSelectedItem();
        if(temp.getStatus()=="Verifying.") {
            DatabaseController.updateOrder(temp.getId(), 1);
            updateTable(FilterField.getText());
            ParentController.createNotification("Order has been updated", 3000);
        }
    }
    public void CancelOrder() throws IOException {
        Order temp = AdminTable.getSelectionModel().getSelectedItem();
        if(temp.getStatus()!="Canceled.") {
            DatabaseController.updateOrder(temp.getId(), -1);
            updateTable(FilterField.getText());
            ParentController.createNotification("Order has been canceled", 3000);
        }
    }
    public void printHistory() throws IOException {
        if (User.getStatus()) {
            String filePath = "2.xlsx";
            List<Order> OrderList = DatabaseController.getOrders();
            Workbook excelWookBook = new XSSFWorkbook();
            Sheet employeeSheet = excelWookBook.createSheet("Orders");
            Row headerRow = employeeSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Photograph");
            headerRow.createCell(1).setCellValue("Contacts");
            headerRow.createCell(2).setCellValue("OrderDate");
            headerRow.createCell(3).setCellValue("Status");
            headerRow.createCell(4).setCellValue("Username");
            headerRow.createCell(5).setCellValue("Id");
            if (OrderList != null) {
                int size = OrderList.size();
                for (int i = 0; i < size; i++) {
                    Order eDto = OrderList.get(i);
                    Row row = employeeSheet.createRow(i + 1);

                    row.createCell(0).setCellValue(eDto.getPhotograph());
                    row.createCell(1).setCellValue(eDto.getUsercontact());
                    row.createCell(2).setCellValue(eDto.getOrderdate().toString());
                    row.createCell(3).setCellValue(eDto.getUserId());
                    row.createCell(4).setCellValue(eDto.getUsername());
                    row.createCell(5).setCellValue(eDto.getId());
                }
            }
            FileOutputStream fOut = new FileOutputStream(filePath);
            excelWookBook.write(fOut);
            fOut.close();
        }
    }
}
