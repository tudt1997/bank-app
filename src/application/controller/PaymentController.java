package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private DatePicker dtPayment;

    @FXML
    private TextField txtInterest;

    private Date startDate;

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

            if (Float.valueOf(txtMonney.getText()) - monney < 0) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Số tiền trả lớn hơn số tiền phải trả!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;

            }

//            long payDateInMili = Date.from(dtPayment.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
//            long diffInMillies = payDateInMili - startDate.getTime();
//            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//            if(diff < 0){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setContentText("Ngày trả tiền phải muộn hơn ngày vay tiền!");
//                alert.showAndWait();
//                return;
//            }
//            else if(payDateInMili > System.currentTimeMillis()){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setContentText("Chưa đến ngày trả tiền!!");
//                alert.showAndWait();
//                return;
//            }


            Providers.updateRecordLoan(id, Float.valueOf(txtMonney.getText()) - monney);

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

    public void setAllField(int id, String type, String monney, float interest, Date dateLoan) {

        this.id=id;

        txtType.setText(type);

        txtMonney.setText(monney);

        txtInterest.setText(String.valueOf(interest));

        startDate = dateLoan;

    }

}
