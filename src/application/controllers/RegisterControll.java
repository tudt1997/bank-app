package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private Button btnNext;

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
	
	//tab3
	
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
	public void executeNext() {

		if(checkTabFirst()) {
			try {
				fillTabSecond();
				
				SingleSelectionModel<Tab> model = tpParent.getSelectionModel();

				model.selectNext();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

					alert.setTitle("Warning dialog!");

					alert.setHeaderText("Data not available in first tab!");

					alert.setContentText("Please enter again!");

					alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setTitle("Warning dialog!");

			alert.setHeaderText("You need to fill out the first form!");

			alert.setContentText("Please enter information in first tab!");

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
			
			ResultSet rs=Providers.getResultSetPerson(txtName.getText(), cbGender.getValue().toString(),
						txtIdCard.getText(), dt, txtAddressPerson.getText(), txtPhoneNumber.getText());
			
			int idCareer=(rs!=null)?rs.getInt(4):0;
			
			System.out.println(idCareer);
			
			ResultSet rsCareer=Providers.getResultSetCareer(idCareer);
			
			txtOrganization.setText(rsCareer.getString(2));
			
			txtAddressOrganization.setText(rsCareer.getString(3));
			
			txtPlaceOfWork.setText(rsCareer.getString(4));
			
			txtIncomePerMonth.setText(rsCareer.getString(5));
			
			txtSpendPerMonth.setText(rsCareer.getString(6));
			
			txtAsset.setText(rs.getString(11));
			

	}

	@FXML
	public void executePrev() {

		SingleSelectionModel<Tab> model = tpParent.getSelectionModel();

		model.selectPrevious();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cbGender.getItems().removeAll(cbGender.getItems());

		cbGender.getItems().addAll("Male", "Female");

		cbGender.getSelectionModel().select(0);
		
		
		//Tab 3
		
		cbTypeLoan.getItems().removeAll(cbTypeLoan.getItems());
		
		cbTypeLoan.getItems().addAll("Vay theo món","Theo hạn mức tín dụng"
				,"Vay thấu chi");
		
		cbTypePurposeLoan.getItems().removeAll(cbTypePurposeLoan.getItems());
		
		cbTypePurposeLoan.getItems().addAll("Vay nhu cầu nhà ở","Vay mua ô tô",
				"Vay du học","Vay sản xuất kinh doanh","Vay tiêu dùng không tài sản đảm bảo",
				"Vay tiêu dùng có tài sản đảm bảo");
		
		cbPayOriginalDebt.getItems().removeAll(cbPayOriginalDebt.getItems());
		
		cbPayOriginalDebt.getItems().addAll("Hàng tháng","Hàng quý","Bán niên", 
				"1 lần vào cuối kỳ");
		
		cbInterestPay.getItems().removeAll(cbInterestPay.getItems());
		
		cbInterestPay.getItems().addAll("Hàng tháng","Khác");
		
		cbWithdrawalFundMethod.getItems().removeAll(cbWithdrawalFundMethod.getItems());
		
		cbWithdrawalFundMethod.getItems().addAll("Tiền mặt","Chuyển khoản");
		
		cbPaymentMethod.getItems().removeAll(cbPaymentMethod.getItems());
		
		cbPaymentMethod.getItems().addAll("Tự động trừ tài khoản","Nộp tiền mặt hoặc chuyển khoản");
		
	}
	
	@FXML
	public void executeAdd() {
		String TypeLoan=cbTypeLoan.getValue();
		
		String TypePurposeLoan=cbTypePurposeLoan.getValue();
		
		double MoneyLoan=0;
		
		try {
			
			MoneyLoan=Double.valueOf(txtMoneyLoan.getText());
			
			java.sql.Date StartDateLoan = null;
			
			if(dtStartDateLoan.getValue()!=null) {
				StartDateLoan=java.sql.Date.valueOf(dtStartDateLoan.getValue());
			}
			else {
				Alert alert=new Alert(AlertType.ERROR);
				
				alert.setTitle("");
				
				alert.setHeaderText("Wrong start date!");
				
				alert.setContentText("Please enter again!");
				
				alert.showAndWait();
				
				return;
				
			}
			
			java.sql.Date EndDate = null;
			
			if(dtEndDate.getValue()!=null) {
				
				EndDate=java.sql.Date.valueOf(dtEndDate.getValue());
				
			}else {
				Alert alert=new Alert(AlertType.ERROR);
				
				alert.setTitle("");
				
				alert.setHeaderText("Wrong end date!");
				
				alert.setContentText("Please enter again!");
				
				alert.showAndWait();
				
				
				return;
			}
			
			String PayOriginalDebt=cbPayOriginalDebt.getValue();
			
			String InterestPay=cbInterestPay.getValue();
			
			String PaymentMethod=cbPaymentMethod.getValue();
			
			String WithdrawalFundMethod=cbWithdrawalFundMethod.getValue();
			
			ResultSet rs=Providers.getRsLoan(TypeLoan, TypePurposeLoan, MoneyLoan, StartDateLoan.toString(), EndDate.toString());
			
			ResultSet rsPerson;
			
			if(rs==null) {
				Alert alert=new Alert(AlertType.ERROR);
				
				alert.setTitle("");
				
				alert.setHeaderText("Information is not available/Incorrect!");
				
				alert.setContentText("Please enter again!");
				
				alert.showAndWait();
				
				return;
				
			}
			else {
				
				String dt = "";
				
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				
				dt = ft.format(ft.parse(dtBirthday.getValue().toString()));
				
				rsPerson=Providers.getResultSetPerson(txtName.getText(), cbGender.getValue().toString(),
						txtIdCard.getText(), dt, txtAddressPerson.getText(), txtPhoneNumber.getText());
				
				ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
				ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
				Alert alertYs = new Alert(AlertType.WARNING,
				        "Do you really want to register?",
				        yes,
				        no);

				alertYs.setTitle("Date format warning");
				Optional<ButtonType> resultPt = alertYs.showAndWait();

				int result=0;
				
				if (resultPt.orElse(no) == yes) {
					
					result=Providers.addRecordLoan(rs.getInt(1), rsPerson.getInt(1), MoneyLoan, StartDateLoan, EndDate, 
							PayOriginalDebt, InterestPay, PaymentMethod, WithdrawalFundMethod);
					
				}
				
				System.out.println("Number of rows is effected "+result);
				
				if(result!=0) {
					Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Register");
			        alert.setHeaderText("Results:");
			        alert.setContentText("Register successfully!");
			 
			        alert.showAndWait();
				}
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
			return;
		}catch(NumberFormatException e) {
			
			Alert alert=new Alert(AlertType.ERROR);
			
			alert.setTitle("");
			
			alert.setHeaderText("Wrong money!");
			
			alert.setContentText("Please enter again!");
			
			alert.showAndWait();
			
			return;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
