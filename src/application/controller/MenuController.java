package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Login;
import application.model.Message;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	@FXML
	Label txtUser;

	@FXML
	public void executeRegisterClick(ActionEvent event) {
		Parent root;
		try {
			root = (Parent) FXMLLoader.load((Login.class.getResource("view/LoanRegistration.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("Đăng ký vay");

			stage.show();

//			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void searchLoan(ActionEvent event) {
		Parent root;
		try {
			root = (Parent) FXMLLoader.load((Login.class.getResource("view/Search.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("Tìm khoản vay");

			stage.show();

            //((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void onClickLogout(Event event) {
		Parent root;
		try {
			root = (Parent) FXMLLoader.load((Login.class.getResource("view/Login.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("");

			stage.show();

			((Node) (event.getSource())).getScene().getWindow().hide();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClickPayment(ActionEvent event){

		Parent root;
		try {
			root = (Parent) FXMLLoader.load((Login.class.getResource("view/SearchForPayment.fxml")));

			Stage stage = new Stage();

			stage.setScene(new Scene(root));

			stage.setTitle("Trả tiền vay");

			stage.show();

//			((Node) (event.getSource())).getScene().getWindow().hide();

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
        txtUser.setText(Message.HELLO + strUser + "!");
	}

}
