package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class PhotoGraphLabel {

    @FXML
    private Label Description;
    @FXML
    private Label photographname;
    @FXML
    private Button commit;
    public void setData(String name, String description){
        this.Description.setText(description);
        this.photographname.setText(name);
    }
    public void acceptOrder() throws SQLException {
        Database.insertOrder(User.getLogin(),photographname.getText());
    }

}
