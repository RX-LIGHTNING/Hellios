package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void ChangeBackgroundImage() throws IOException {
        if(!PictureURL.getText().isEmpty()) {
            ParentController.setBackgroundImage(PictureURL.getText());
            ParentController.createNotification("Background changed",3000);
        }
    }
    public void ChangeTheme() throws IOException {
        if(!ThemeURL.getText().isEmpty()) {
            ParentController.setApplicationTheme(ThemeURL.getText());
            ParentController.createNotification("Theme changed",3000);
        }
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
    public void backgroundFileSelecter(){

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Path pathAbsolute = Paths.get(fileChooser.showOpenDialog(stage).getPath());
        PictureURL.setText(pathAbsolute.toString());
    }

    public void themesFileSelector(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSS styles", "*.css"));
        Path pathAbsolute = Paths.get(fileChooser.showOpenDialog(stage).getPath());
        ThemeURL.setText(pathAbsolute.toString());
    }
}
