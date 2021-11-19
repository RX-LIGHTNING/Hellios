package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {
    @FXML
    private TableView<Order> Table;
    @FXML
    private TableColumn Photograph;
    @FXML
    private TableColumn Status;
    @FXML
    private TableColumn UserContact;
    @FXML
    private TableColumn Date;
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
        UserContact.setCellValueFactory(new PropertyValueFactory<>("usercontact"));
        Date.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        for (int i = 0; i < OrderList.size(); i++) {

            if (filter!=""&& OrderList.get(i).getPhotograph().contains(filter)) {
                if(checkbox && OrderList.get(i).getStatus().equals("In progress."))
                Table.getItems().add(OrderList.get(i));
                else if (!checkbox){
                    Table.getItems().add(OrderList.get(i));
                }
            }
            else if (Objects.equals(filter, "")){
                if(checkbox && OrderList.get(i).getStatus().equals("In progress."))
                    Table.getItems().add(OrderList.get(i));
                else if (!checkbox){
                    Table.getItems().add(OrderList.get(i));
                }
            }
        }

    }
    public void filterTable() throws IOException {
        updateTable(FilterField.getText(), FilterCheck.isSelected());
        ParentController.createNotification("Table has been filtered",3000);
    }
    public void CancelOrder() throws IOException {
        Order temp = Table.getSelectionModel().getSelectedItem();
        if(temp.getStatus()!="Done.") {
            DatabaseController.updateOrder(temp.getId(), -1);
            updateTable(FilterField.getText(),FilterCheck.isSelected());
            ParentController.createNotification("Order has been canceled", 1000);
        }
    }
    public void printHistory() throws IOException {

        String filePath = "1.xlsx";
        List<Order> OrderList = DatabaseController.getOrders();
        Workbook excelWookBook = new XSSFWorkbook();
        Sheet employeeSheet = excelWookBook.createSheet("Orders");
        Row headerRow = employeeSheet.createRow(0);

        headerRow.createCell(0).setCellValue("Photograph");
        headerRow.createCell(1).setCellValue("Contacts");
        headerRow.createCell(2).setCellValue("OrderDate");
        headerRow.createCell(3).setCellValue("Status");
        if(OrderList!=null)
        {
            int size = OrderList.size();
            for(int i=0;i<size;i++)
            {
                Order eDto = OrderList.get(i);
                Row row = employeeSheet.createRow(i+1);

                row.createCell(0).setCellValue(eDto.getPhotograph());
                row.createCell(1).setCellValue(eDto.getUsercontact());
                row.createCell(2).setCellValue(eDto.getOrderdate());
                row.createCell(3).setCellValue(eDto.getStatus());

            }
        }
        FileOutputStream fOut = new FileOutputStream(filePath);
        excelWookBook.write(fOut);
        fOut.close();
    }
    public void printAgreement() throws IOException, InvalidFormatException {
        Order temp = Table.getSelectionModel().getSelectedItem();
        if(Objects.nonNull(temp) && Objects.equals(temp.getStatus(), "In progress.") ||Objects.equals(temp.getStatus(), "Done.")) {
            XWPFDocument document = new XWPFDocument(OPCPackage.open("input.docx"));
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    text = text.replace("name", temp.getUsername());
                    text = text.replace("photo", temp.getPhotograph());
                    text = text.replace("date", temp.getOrderdate().toString());
                    run.setText(text,0);
                }
            }
            document.write(new FileOutputStream("output.docx"));
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File("output.docx"));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }
}

