package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import application.LoginUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegisterControll implements Initializable {

	@FXML
	private TabPane tpParent;

	@FXML
	private Tab tp1;

	@FXML
	private Tab tp2;

	@FXML
	private Tab tp3;
	
	@FXML
	private Button btnPrev;
	
	@FXML
	private Button btnNext;

    @FXML
    private Button btnSearch;

	@FXML
	private Button btnFinish;
	
	// tab 1
	@FXML
	private TextField txtName;

	@FXML
	private ChoiceBox<String> cbGender;

	@FXML
	private TextField txtIdCard;

	@FXML
	private DatePicker dtBirthday;

	@FXML
	private TextField txtAddressPerson;

	@FXML
	private TextField txtPhoneNumber;

	// tab2
	@FXML
	private TextField txtOrganization;

	@FXML
	private TextField txtAddressOrganization;

	@FXML
	private TextField txtPlaceOfWork;

	@FXML
	private TextField txtIncomePerMonth;

	@FXML
	private TextField txtSpendPerMonth;

	@FXML
	private TextField txtAsset;

	// tab3

	@FXML
	private ChoiceBox<String> cbTypeLoan;

	@FXML
	private ChoiceBox<String> cbTypePurposeLoan;

	@FXML
	private TextField txtMoneyLoan;

	@FXML
	private DatePicker dtStartDateLoan;

	@FXML
	private DatePicker dtEndDate;

	@FXML
	private ChoiceBox<String> cbPayOriginalDebt;

	@FXML
	private ChoiceBox<String> cbInterestPay;

	@FXML
	private ChoiceBox<String> cbPaymentMethod;

	@FXML
	private ChoiceBox<String> cbWithdrawalFundMethod;

    @FXML
    private TextField txtInterest;

    @FXML
    private Button btnbtnDisplayInterest;

    @FXML
    private Button btnPrintBill;

	@FXML
	public void executeNext() {
		if (!tp1.isDisable()) {
			if (checkTabFirst()) {
//			if (true) {
				btnPrev.setDisable(false);
				tp1.setDisable(true);
				tp2.setDisable(false);
	//			tpParent.getSelectionModel().select(tp2);
				
				try {
					fillTabSecond();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SingleSelectionModel<Tab> model = tpParent.getSelectionModel();
				model.selectNext();
	
			}
        } else if (!tp2.isDisable()) {
            btnFinish.setDisable(false);
            btnNext.setDisable(true);
            tp2.setDisable(true);
            tp3.setDisable(false);
            btnPrintBill.setDisable(false);

            SingleSelectionModel<Tab> model = tpParent.getSelectionModel();
            model.selectNext();
        } else {
            btnPrintBill.setDisable(false);
        }

	}

	public boolean checkTabFirst() {

		if (!txtName.getText().equals("") && !txtIdCard.getText().equals("") && !txtAddressPerson.getText().equals("")
				&& !txtPhoneNumber.getText().equals("") && dtBirthday.getValue() != null) {

			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

			String dt = "";

			try {

				dt = ft.format(ft.parse(dtBirthday.getValue().toString()));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (Providers.checkPersonAvailable(txtName.getText(), cbGender.getValue().toString(),
						txtIdCard.getText(), dt, txtAddressPerson.getText(), txtPhoneNumber.getText())) {

					return true;

				} else {
					Alert alert = new Alert(AlertType.WARNING);

                    alert.setTitle("Cảnh báo!");

                    alert.setHeaderText("Dữ liệu không tồn tại ở mẫu đầu tiên!");

                    alert.setContentText("Xin mời nhập lại!");

					alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Cảnh báo!");

            alert.setHeaderText("Bạn cần nhập đầy đủ thông tin vào mẫu đầu tiên!");

            alert.setContentText("Xin nhập lại thông tin vào mẫu đầu tiêns!");

			alert.showAndWait();

		}
		return false;

	}

	public void fillTabSecond() throws SQLException {

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		String dt = "";

		try {

			dt = ft.format(ft.parse(dtBirthday.getValue().toString()));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ResultSet rs = Providers.getResultSetPerson(txtName.getText(), cbGender.getValue().toString(),
		//		txtIdCard.getText(), dt, txtAddressPerson.getText(), txtPhoneNumber.getText());
		ResultSet rs = Providers.getResultSetPersonById(txtIdCard.getText());

//		String strFullName = Providers.getFullName(rs.getInt(4));
//
//		txtName.setTextDetails(strFullName);
//
//		cbGender.setValue(rs.getString(6));

//		dtBirthday.setValue(LocalDate.parse(rs.getString(10)));
//
//		//txtAddressPerson.setTextDetails();
//
//		txtPhoneNumber.setTextDetails(rs.getString(9));
		
		int idCareer = (rs != null) ? rs.getInt(4) : 0;

		System.out.println(idCareer);

		ResultSet rsCareer = Providers.getResultSetCareer(idCareer);

		txtOrganization.setText(rsCareer.getString(2));

		txtAddressOrganization.setText(rsCareer.getString(3));

		txtPlaceOfWork.setText(rsCareer.getString(4));
		System.out.println(rsCareer.getDouble(5));;
		txtIncomePerMonth.setText(String.format("%.2f", rsCareer.getDouble(5)));

		txtSpendPerMonth.setText(String.format("%.2f", rsCareer.getDouble(6)));

		txtAsset.setText(String.format("%.2f", rs.getDouble(11)));

	}

	@FXML
	public void executePrev() {

		SingleSelectionModel<Tab> model = tpParent.getSelectionModel();
		model.selectPrevious();
		
		if (!tp2.isDisable()) {
			btnPrev.setDisable(true);
			tp1.setDisable(false);
			tp2.setDisable(true);
            btnFinish.setDisable(true);
            btnPrintBill.setDisable(true);
		} else if (!tp3.isDisable()) {
			btnNext.setDisable(false);
			btnFinish.setDisable(true);
			tp2.setDisable(false);
			tp3.setDisable(true);
            btnPrintBill.setDisable(false);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cbGender.getItems().removeAll(cbGender.getItems());

		cbGender.getItems().addAll("Nam", "Nữ");

		cbGender.getSelectionModel().select(0);

		// Tab 3

		cbTypeLoan.getItems().removeAll(cbTypeLoan.getItems());

		cbTypeLoan.getItems().addAll("Vay theo món", "Theo hạn mức tín dụng", "Vay thấu chi");

		cbTypePurposeLoan.getItems().removeAll(cbTypePurposeLoan.getItems());

		cbTypePurposeLoan.getItems().addAll("Vay nhu cầu nhà ở", "Vay mua ô tô", "Vay du học",
				"Vay sản xuất kinh doanh", "Vay tiêu dùng không tài sản đảm bảo", "Vay tiêu dùng có tài sản đảm bảo");

		cbPayOriginalDebt.getItems().removeAll(cbPayOriginalDebt.getItems());

		cbPayOriginalDebt.getItems().addAll("Hàng tháng", "Hàng quý", "Bán niên", "1 lần vào cuối kỳ");

		cbInterestPay.getItems().removeAll(cbInterestPay.getItems());

		cbInterestPay.getItems().addAll("Hàng tháng", "Khác");

		cbWithdrawalFundMethod.getItems().removeAll(cbWithdrawalFundMethod.getItems());

		cbWithdrawalFundMethod.getItems().addAll("Tiền mặt", "Chuyển khoản");

		cbPaymentMethod.getItems().removeAll(cbPaymentMethod.getItems());

		cbPaymentMethod.getItems().addAll("Tự động trừ tài khoản", "Nộp tiền mặt hoặc chuyển khoản");

	}

	@FXML
    public void executeAdd() {
        String TypeLoan = cbTypeLoan.getValue();

        String TypePurposeLoan = cbTypePurposeLoan.getValue();

        double MoneyLoan = 0;

        try {

            MoneyLoan = Double.valueOf(txtMoneyLoan.getText());

            if (Double.valueOf(MoneyLoan) <= 0) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Nhập không đúng định dạng tiền!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;
            }

            java.sql.Date StartDateLoan = null;

            if (dtStartDateLoan.getValue() != null) {
                StartDateLoan = java.sql.Date.valueOf(dtStartDateLoan.getValue());
            } else {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Sai ngày bắt đầu!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;

            }

            java.sql.Date EndDate = null;

            if (dtEndDate.getValue() != null) {

                EndDate = java.sql.Date.valueOf(dtEndDate.getValue());

            } else {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Sai ngày kết thúc!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;
            }

            if (!dtEndDate.getValue().isAfter(dtStartDateLoan.getValue())) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Sai khoảng ngày!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;

            }

            String PayOriginalDebt = cbPayOriginalDebt.getValue();

            String InterestPay = cbInterestPay.getValue();

            String PaymentMethod = cbPaymentMethod.getValue();

            String WithdrawalFundMethod = cbWithdrawalFundMethod.getValue();

            ResultSet rs = Providers.getRsLoan(TypeLoan, TypePurposeLoan, MoneyLoan, StartDateLoan.toString(),
                    EndDate.toString());

            txtInterest.setText(rs.getFloat(6) * 100 + "%");

            ResultSet rsPerson;

            if (rs == null) {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Thông tin không chính xác hoặc không tồn tại!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();

                return;

            } else {

                String dt = "";

                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

                dt = ft.format(ft.parse(dtBirthday.getValue().toString()));

                rsPerson = Providers.getResultSetPerson(txtName.getText(), cbGender.getValue().toString(),
                        txtIdCard.getText(), dt, txtAddressPerson.getText(), txtPhoneNumber.getText());

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alertYs = new Alert(AlertType.WARNING, "Bạn thực sự muốn đăng ký vay?", yes, no);

                alertYs.setTitle("Cảnh báo định dạng ngày!");
                Optional<ButtonType> resultPt = alertYs.showAndWait();

                int result = 0;

                if (resultPt.orElse(no) == yes) {

                    result = Providers.addRecordLoan(rs.getInt(1), rsPerson.getInt(1), MoneyLoan, StartDateLoan,
                            EndDate, PayOriginalDebt, InterestPay, PaymentMethod, WithdrawalFundMethod);

                }

                System.out.println("Number of rows is effected " + result);

                if (result != 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Đăng ký");
                    alert.setHeaderText("Kết quả:");
                    alert.setContentText("Đăng ký thành công!");

                    alert.showAndWait();
                }

            }

        } catch (SQLException e) {
            // TODO: handle exception

            e.printStackTrace();

            return;
        } catch (NumberFormatException e) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Số tiền sai!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    public void executeSearch() {

        ResultSet rs = null;
        String strAddress = null;
        try {
            rs = Providers.getResultSetPersonById(txtIdCard.getText());
            if (rs != null) {
                strAddress = Providers.getAddress(rs.getInt(3));

                String strFullName = Providers.getFullName(rs.getInt(4));

                txtName.setText(strFullName);

                cbGender.setValue(rs.getString(6));

                dtBirthday.setValue(LocalDate.parse(rs.getString(10)));

                txtAddressPerson.setText(strAddress);

                txtPhoneNumber.setText(rs.getString(9));
            } else {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Không tồn tại số cmt nào như vậy!");

                alert.setContentText("Xin mời nhập lại!");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void executeDisplayInterest() {

        double MoneyLoan = 0;

        MoneyLoan = Double.valueOf(txtMoneyLoan.getText());

        if (Double.valueOf(MoneyLoan) <= 0) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Nhập không đúng định dạng tiền!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

        String TypeLoan = cbTypeLoan.getValue();

        String TypePurposeLoan = cbTypePurposeLoan.getValue();

        String PayOriginalDebt = cbPayOriginalDebt.getValue();

        String InterestPay = cbInterestPay.getValue();

        String PaymentMethod = cbPaymentMethod.getValue();

        String WithdrawalFundMethod = cbWithdrawalFundMethod.getValue();

        java.sql.Date StartDateLoan = null;

        if (dtStartDateLoan.getValue() != null) {
            StartDateLoan = java.sql.Date.valueOf(dtStartDateLoan.getValue());
        } else {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Sai ngày bắt đầu!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;

        }

        java.sql.Date EndDate = null;

        if (dtEndDate.getValue() != null) {

            EndDate = java.sql.Date.valueOf(dtEndDate.getValue());

        } else {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Sai ngày kết thúc!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

        if (!dtEndDate.getValue().isAfter(dtStartDateLoan.getValue())) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Sai khoảng ngày!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;

        }

        ResultSet rs = null;
        try {
            rs = Providers.getRsLoan(TypeLoan, TypePurposeLoan, MoneyLoan, StartDateLoan.toString(),
                    EndDate.toString());

            txtInterest.setText(rs.getFloat(6) * 100 + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void executePrint() {

        if (cbTypeLoan.getValue() == null ||
                cbTypePurposeLoan.getValue() == null ||
                txtMoneyLoan.getText() == null ||
                dtStartDateLoan.getValue() == null ||
                dtEndDate.getValue() == null ||
                txtInterest.getText() == null ||
                cbPayOriginalDebt.getValue() == null ||
                cbInterestPay.getValue() == null ||
                cbPaymentMethod.getValue() == null ||
                cbWithdrawalFundMethod.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Nhập thiếu thông tin!");

            alert.setContentText("Xin mời nhập thêm!");

            alert.showAndWait();

            return;
        }

        double MoneyLoan = 0;

        MoneyLoan = Double.valueOf(txtMoneyLoan.getText());

        if (Double.valueOf(MoneyLoan) <= 0) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Nhập không đúng định dạng tiền!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

        String TypeLoan = cbTypeLoan.getValue();

        String TypePurposeLoan = cbTypePurposeLoan.getValue();

        String PayOriginalDebt = cbPayOriginalDebt.getValue();

        String InterestPay = cbInterestPay.getValue();

        String PaymentMethod = cbPaymentMethod.getValue();

        String WithdrawalFundMethod = cbWithdrawalFundMethod.getValue();

        java.sql.Date StartDateLoan = null;

        if (dtStartDateLoan.getValue() != null) {
            StartDateLoan = java.sql.Date.valueOf(dtStartDateLoan.getValue());
        } else {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Sai ngày bắt đầu!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;

        }

        java.sql.Date EndDate = null;

        if (dtEndDate.getValue() != null) {

            EndDate = java.sql.Date.valueOf(dtEndDate.getValue());

        } else {
            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("");

            alert.setHeaderText("Sai ngày kết thúc!");

            alert.setContentText("Xin mời nhập lại!");

            alert.showAndWait();

            return;
        }

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader((LoginUI.class.getResource("view/PrintBill.fxml")));

            root = (Parent) loader.load();

            BillCtr billCtr = loader.getController();

            if (txtInterest.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("");

                alert.setHeaderText("Chưa hiển thị lại suất!");

                alert.setContentText("Xin mời nhấn xem lãi suất tương ứng!");

                alert.showAndWait();

                return;
            }

            billCtr.setData("Họ tên:" + txtName.getText() + "\n" +
                    "Hình thức vay: " + TypeLoan + "\n" +
                    "Mục đích vay: " + TypePurposeLoan + "\n" +
                    "Số tiền vay cấp hạn mức: " + PayOriginalDebt + "\n" +
                    "Thời hạn vay:" + "\n" +
                    "Ngày bắt đầu: " + StartDateLoan.toString() + "\n" +
                    "Ngày kết thúc: " + EndDate.toString() + "\n" +
                    "Lãi suất: " + txtInterest.getText() + "\n" +
                    "Kế hoạch trả nợ:" + "\n" +
                    "Kỳ trả nợ gốc: " + cbPayOriginalDebt.getValue() + "\n" +
                    "Kỳ trả lãi: " + cbInterestPay.getValue() +
                    "Phương thức trả nợ: " + cbPaymentMethod.getValue() + "\n" +
                    "Phương thức rút vốn: " + cbWithdrawalFundMethod.getValue());

            Stage stage = new Stage();

            stage.setScene(new Scene(root));

            stage.setTitle("In hóa đơn vay");

            stage.show();

            //((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

	public Tab getTp1() {
		return tp1;
	}

	public Tab getTp2() {
		return tp2;
	}

	public Tab getTp3() {
		return tp3;
	}

	public Button getBtnPrev() {
		return btnPrev;
	}

	public Button getBtnNext() {
		return btnNext;
	}

	public Button getBtnFinish() {
		return btnFinish;
	}
}
