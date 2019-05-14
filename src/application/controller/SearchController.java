package application.controller;

import application.Login;
import application.model.SearchResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public SearchController() {

    }

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
    private TableColumn column1Rs, column2Rs, column3Rs, column4Rs;

	@FXML
	public void btnSearchClick(ActionEvent event) {
		try {
            String searchType = "";
            if (cbType.getSelectionModel().getSelectedItem().toString().equals("Mã khoản vay"))
                searchType = "LoanId";
            else if (cbType.getSelectionModel().getSelectedItem().toString().equals("Số CMND"))
                searchType = "IdentityCard";

			String keyWord = searchKeyWord.getText();

            ArrayList<SearchResult> searchResultList = Providers.searchLoanRecord(searchType, keyWord);

			tableSearchResult.getItems().clear();

            column1Rs.setCellValueFactory(new PropertyValueFactory<>("loanID"));
            column2Rs.setCellValueFactory(new PropertyValueFactory<>("accID"));
            column3Rs.setCellValueFactory(new PropertyValueFactory<>("personname"));
            column4Rs.setCellValueFactory(new PropertyValueFactory<>("amount"));

            tableSearchResult.scrollToColumnIndex(0);

            if (searchResultList.size() == 0)
                alertNoLoanFound();
            else
                for (SearchResult searchrs : searchResultList) {
                    searchrs.print();
                    tableSearchResult.getItems().add(searchrs);
                }

            tableSearchResult.setOnMouseClicked(event2 -> {
                if (event2.getClickCount() == 2) {
                    SearchResult clicked = (SearchResult) tableSearchResult.getSelectionModel().getSelectedItem();
                    viewDetails(clicked);
                }
            });

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e1) {
		    alertNoLoanFound();
		}
	}

    public void viewDetails(SearchResult sr) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Login.class.getResource("view/PersonalDetails.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();

        PersonalDetailsController pd = loader.getController();
        pd.setTextDetails(sr);
    }

    public void alertNoLoanFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("");
        alert.setHeaderText("Không tìm thấy khoản vay!");

        alert.showAndWait();
    }
    @FXML
    public void executeBack(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
