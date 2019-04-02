package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.LoginUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuControll implements Initializable {

	@FXML
	Label txtUser;
	
	@FXML
	public void executeRegisterClick(ActionEvent event) {
		Parent root;
		try {
			root = (Parent) FXMLLoader.load((LoginUI.class.getResource("view/DangKyVay.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("Registration");

			stage.show();

			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void searchLoan(ActionEvent event) {
		Parent root;
		try {
			root = (Parent) FXMLLoader.load((LoginUI.class.getResource("view/TimKiem.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("Registration");

			stage.show();

			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public void initData(String strUser) {
		txtUser.setText("Hello "+strUser+"!");
	}

}
