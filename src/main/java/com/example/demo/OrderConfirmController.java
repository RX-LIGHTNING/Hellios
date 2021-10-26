package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Objects;


public class OrderConfirmController {
    @FXML
    private DatePicker DataPicker;
    @FXML
    private TextField UserNameField;
    @FXML
    private CheckBox ContactCheckBox;
    @FXML
    private TextField ContactText;

    private MainMenuController menuController;
    private String PhotographName;
    private String UserContact;
    public void setData(MainMenuController menuController, String photographs){
            this.menuController = menuController;
            this.PhotographName = photographs;
    }
    public void confirmOrder() throws IOException {

        if(ContactCheckBox.isSelected()) UserContact= User.getContact();
        else UserContact = ContactText.getText();

        if(Objects.nonNull(DataPicker.getValue()) && !UserContact.isEmpty() && Objects.nonNull(UserNameField)) {
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(DataPicker.getValue());
            DatabaseController.insertOrder(PhotographName, UserNameField.getText(), UserContact,gettedDatePickerDate);
            menuController.createNotification("Your order has been created",3000);
            menuController.setPhotographsListSlide();
            
            //
        }



    }
}
