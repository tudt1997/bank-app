package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class BillController implements Initializable {

    @FXML
    private TextArea txtaBill;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void executeBack(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void setData(String text) {
        txtaBill.setText(text);
    }

}
