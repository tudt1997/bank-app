package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    private int id;

    @FXML
    private javafx.scene.control.Button btnConfirm;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtMonney;

    @FXML
    private TextField txtPaymentMonney;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void executeBack(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void executeConfirm(ActionEvent event){

        if(txtPaymentMonney.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Số tiền nhập không chính xác!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

        float monney;

        try {

            monney=Float.valueOf(txtPaymentMonney.getText());

            if((monney<=0)) throw new NumberFormatException();

        }catch (NumberFormatException ex){

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Số tiền nhập không chính xác!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;

        }

        try {

            if(Float.valueOf(txtMonney.getText())-monney<0){

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Số tiền trả lớn hơn số tiền phải trả!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;

            }

            Providers.updateRecordLoan(id,Float.valueOf(txtMonney.getText())-monney);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trả tiền");
            alert.setHeaderText("Kết quả:");
            alert.setContentText("Xác nhận thành công!");

            alert.showAndWait();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Số tiền nhập không chính xác!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

    }

    public void setAllField(int id,String type,String monney){

        this.id=id;

        txtType.setText(type);

        txtMonney.setText(monney);

    }

}
