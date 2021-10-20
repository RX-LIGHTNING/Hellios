package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController {
    public Text currentContact;
    @FXML
    private TextField PictureURL;
    @FXML
    private TextField ThemeURL;
    @FXML
    private TextField ContactTextField;
    MainMenuController ParentController;
    public void ChangeBackgroundImage(){
        ParentController.setBackgroundImage(PictureURL.getText());
    }
    public void ChangeTheme(){
        ParentController.setApplicationTheme(ThemeURL.getText());
    }

    public void setData(MainMenuController mainMenuController) {
        ParentController = mainMenuController;
        currentContact.setText("Current contact information: "+User.getContact());
    }
    public void changeContacts(){
        if(!ContactTextField.getText().isEmpty()) {
            DatabaseController.userContactUpdate(ContactTextField.getText());
            User.setContact(ContactTextField.getText());
            currentContact.setText("Current contact information: " + User.getContact());
        }
    }

}
