package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoanRegistration extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
			 * scene.getStylesheets().add(getClass().getResource("application.css").
			 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show();
			 */

			AnchorPane home = (AnchorPane) FXMLLoader.load((getClass().getResource("view/LoanRegistration.fxml")));

			primaryStage.setTitle("Đăng ký vay");

			Scene scene = new Scene(home);

			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.setResizable(false);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
