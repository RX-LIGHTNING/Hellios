package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
public class OptionsController {
    @FXML
    private TextField PictureURL;
    @FXML
    private TextField ThemeURL;
    MainMenuController ParentController;
    public void ChangeBackgroundImage(){
        ParentController.setBackgroundImage(PictureURL.getText());
    }
    public void ChangeTheme(){
        ParentController.setApplicationTheme(ThemeURL.getText());
    }

    public void setData(MainMenuController mainMenuController) {
        ParentController = mainMenuController;
    }
}
