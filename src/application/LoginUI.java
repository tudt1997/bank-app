package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginUI extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").
			 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show();
			 */

			AnchorPane home = (AnchorPane) FXMLLoader.load((getClass().getResource("view/Login.fxml")));

			primaryStage.setTitle("Đăng nhập");

			primaryStage.setScene(new Scene(home));

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
