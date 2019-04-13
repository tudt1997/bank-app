package application.controllers;

import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.LoginUI;
import application.model.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.sql.*;

public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public boolean executeLogin(ActionEvent event) {
		try {

			String username = txtUsername.getText();

			String password = txtPassword.getText();

			if (!username.equals("") && !password.equals("")) {
				if (Providers.checkLogin(username, password)) {
					Alert alert = new Alert(AlertType.INFORMATION);
	
					alert.setTitle("");
	
					alert.setHeaderText(Message.loginSuccessMsg);
	
					alert.setContentText(Message.helloMsg + username);
	
					alert.showAndWait();
	
					FXMLLoader loader = new FXMLLoader((LoginUI.class.getResource("view/Menu.fxml")));
	
					// Parent
					// root=(Parent)FXMLLoader.load((LoginUI.class.getResource("view/Menu.fxml")));
	
					Stage stage = new Stage();
	
					// stage.setScene(new Scene(root));
					stage.setScene(new Scene((Pane) loader.load()));
	
					stage.setTitle("Registration");
	
					MenuControll controller = loader.<MenuControll>getController();
	
					// set Username for new Window by using DI and event Bus
					controller.initData(username);
	
					stage.show();
	
					((Node) (event.getSource())).getScene().getWindow().hide();
				} else {
					Alert alert = new Alert(AlertType.ERROR);

					alert.setTitle("");

					alert.setHeaderText(Message.loginFailMsg);

					alert.setContentText(Message.wrongUsernamePasswordMsg);

					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.ERROR);

				alert.setTitle("");

				alert.setHeaderText(Message.loginFailMsg);

				alert.setContentText(Message.emptyUsernamePasswordMsg);

				alert.showAndWait();
			}

			/*
			 * btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			 * 
			 * @Override public void handle(ActionEvent event) { } });
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
