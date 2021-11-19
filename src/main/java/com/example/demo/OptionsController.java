package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OptionsController{
    public Text currentContact;
    @FXML
    private CheckBox radiobutton;
    @FXML
    private TextField PictureURL;
    @FXML
    private TextField ContactTextField;
    MainMenuController ParentController;
    public void ChangeBackgroundImage() throws IOException {
        if(!PictureURL.getText().isEmpty()) {
            ParentController.setBackgroundImage(PictureURL.getText());
            ParentController.createNotification("Background changed",3000);
            FileWriter writer = new FileWriter("Options.dat", false);
            writer.append(PictureURL.getText());
            writer.append("\n");
            writer.flush();
        }
    }
    public void dropSettings() throws IOException{

        FileWriter writer = new FileWriter("Options.dat", false);
        writer.append(null);
        writer.flush();
        ParentController.setBackgroundImage("");
    }


    public void setData(MainMenuController mainMenuController) {
        ParentController = mainMenuController;
        currentContact.setText("Current contact information: "+User.getContact());
        Button temp = (Button) ParentController.NavBarBox.getChildren().get(2);
        if(Objects.equals(temp.getText(), "Main")){
            radiobutton.setSelected(true);
        }
    }
    public void changeContacts(){
        if(!ContactTextField.getText().isEmpty() &&Pattern.matches("\\+\\d{12}",ContactTextField.getText()))  {
            DatabaseController.userContactUpdate(ContactTextField.getText());
            User.setContact(ContactTextField.getText());
            currentContact.setText("Current contact information: " + User.getContact());
        }
        else{
            ContactTextField.setStyle("-fx-border-color:red;");
        }
    }
    public void casualFunctionReturn(){
        if(radiobutton.isSelected()){
            ParentController.backCasualMenu();
        }
        else {
            ParentController.deleteCasualMenu();
        }
    }
    public void backgroundFileSelecter() throws IOException {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Path pathAbsolute = Paths.get(fileChooser.showOpenDialog(stage).getPath());
        PictureURL.setText(pathAbsolute.toString());
    }

}
