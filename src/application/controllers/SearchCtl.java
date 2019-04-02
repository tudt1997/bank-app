package application.controllers;

import application.LoginUI;
import application.model.SearchResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchCtl implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private Button btnSearch;

    @FXML
    private ChoiceBox cbType;

    @FXML
    private TextField searchKeyWord;

    @FXML
    private TableView tableSearchResult;

    @FXML
    private TableColumn column1Rs;

    @FXML
    private TableColumn column2Rs;

    @FXML
    private TableColumn column3Rs;

    @FXML
    private TableColumn column4Rs;

    @FXML
    public void btnSearchClick(ActionEvent event) {
        try {

            String searchType= cbType.getSelectionModel().getSelectedItem().toString();
            String keyWord= searchKeyWord.getText();

            SearchResult searchResult = Providers.searchLoanRecord(searchType, keyWord);
            System.out.println(searchResult.getLoanID());
            System.out.println(searchResult.getAccID());
            System.out.println(searchResult.getPersonname());
            System.out.println(searchResult.getAmount());

            tableSearchResult.getItems().clear();

            column1Rs.setCellValueFactory(new PropertyValueFactory<>("loanID"));
            column2Rs.setCellValueFactory(new PropertyValueFactory<>("accID"));
            column3Rs.setCellValueFactory(new PropertyValueFactory<>("personname"));
            column4Rs.setCellValueFactory(new PropertyValueFactory<>("amount"));

            tableSearchResult.getItems().add(searchResult);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(NullPointerException e1){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("");
            alert.setHeaderText("Loan not found!");

            alert.showAndWait();

            return;
        }
    }
}
