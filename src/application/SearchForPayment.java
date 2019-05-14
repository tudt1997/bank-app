package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchForPayment extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            /*
             * BorderPane root = new BorderPane(); Scene scene = new Scene(root,400,400);
             * scene.getStylesheets().add(getClass().getResource("application.css").
             * toExternalForm()); primaryStage.setScene(scene); primaryStage.show();
             */

            BorderPane home = FXMLLoader.load((getClass().getResource("view/SearchForPayment.fxml")));

            primaryStage.setTitle("Tìm khoản vay");

            primaryStage.setScene(new Scene(home));

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
