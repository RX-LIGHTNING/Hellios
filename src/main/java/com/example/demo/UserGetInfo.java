package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Objects;

public class UserGetInfo {
    @FXML
    private Text LoginText;
    @FXML
    private Text ContactsText;
    @FXML
    private Text StatusText;
    @FXML
    private TextField IdTextField;
    public void getUserInfo() throws SQLException {
        if(User.getStatus()){
          SelectedUser temp = DatabaseController.getUserInfo(Integer.parseInt(IdTextField.getText()));
          if(Objects.nonNull(temp)) {
              LoginText.setText("User login: " + temp.getLogin());
              ContactsText.setText("User Contacts: " + temp.getContact());
              StatusText.setText("Is admin?: " + Boolean.toString(temp.getStatus()));
          }
        }
    }
}
