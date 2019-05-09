package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import application.LoginUI;
import application.model.PersonalDetails;
import application.model.SearchResult;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Providers {

	public static Connection connection;

	private static String url = "jdbc:mysql://localhost:3306/dbcl"
			+ "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true?useUnicode=true&"
			+ "characterEncoding=UTF-8&autoReconnect=true";

	private static String user = "root";

	private static String pass = "";

	public Providers() throws SQLException {
		connection = DriverManager.getConnection(url, user, pass);
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static boolean checkLogin(String username, String password) throws SQLException {
		connection = getConnection();

		Statement stmt = connection.createStatement();

		boolean flag = true;

		ResultSet rs = stmt.executeQuery(
				"Select * from account where Username='" + username + "'" + "and Password='" + password + "'");

		if (rs.next()) {

			stmt.close();

			rs.close();

			return true;

		}
		return false;
	}

	public static boolean checkPersonAvailable(String name, String gender, String identityCard, String birthOfDate,
			String address, String phoneNumber) throws SQLException {
		connection = getConnection();

        String query = "Select * from person where Gender='" + gender + "'and IdentityCard='" + identityCard
				+ "'and DateObBirth='" + birthOfDate + "'and PhoneNumber='" + phoneNumber + "'";

//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		int addressId = 0;

		int nameId = 0;

		if (!rs.next()) {
			return false;
		} else {

			addressId = rs.getInt(3);

			nameId = rs.getInt(4);

			System.out.println(addressId);

			System.out.println(nameId);

			if (!checkAddressBaseId(addressId, address) && !checkNameBaseName(nameId, name)) {
				return true;
			}

		}

		return true;
	}

	public static boolean checkAddressBaseId(int id, String Address) throws SQLException {
		connection = getConnection();

		System.out.println(Address);

		String query = "Select * from address where NameOfAddress='" + Address + "'" + "and Id='" + id + "'";

//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		if (!rs.next())
			return false;

		return true;
	}

	public static boolean checkNameBaseName(int id, String name) throws SQLException {
		connection = getConnection();

		System.out.println(name);

		String[] fragmentsName = name.split(" ");

		String query = "Select * from fullname where FirstName='" + fragmentsName[0] + "' and" + " MidName='"
				+ fragmentsName[1] + "' and LastName='" + fragmentsName[2] + "' and Id='" + id + "'";

//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		if (!rs.next())
			return false;

		return true;
	}

    public static ArrayList<SearchResult> searchLoanRecord(String searchType, String keyWord) throws SQLException {
        ArrayList<SearchResult> returnList = new ArrayList<SearchResult>();

		connection = getConnection();
		Statement stmt = connection.createStatement();
        Statement stmt1 = connection.createStatement();
        Statement stmt2 = connection.createStatement();

		String query = "SELECT LoanId, PersonId, AmountOfMoney FROM recordloan WHERE " + searchType + " LIKE '%"
				+ keyWord + "%'";
		ResultSet rsRecordLoan = stmt.executeQuery(query);
		// Ma khoan vay & so tien vay
        String makhoanvay;
        String sotienvay;
        String sotaikhoan;
        String chutaikhoan;

        ResultSet rsFullname;
        ResultSet rsPerson;

        while (rsRecordLoan.next()) {
            System.out.println("abcc");
            makhoanvay = Integer.toString(rsRecordLoan.getInt(1));
            int personId = rsRecordLoan.getInt(2);
            sotienvay = Float.toString(rsRecordLoan.getFloat(3));

            query = "SELECT AccountId, FullNameId FROM person WHERE Id=" + personId;
            rsPerson = stmt1.executeQuery(query);
            // So tai khoan
            if (!rsPerson.next())
                return null;
            sotaikhoan = Integer.toString(rsPerson.getInt(1));

            query = "SELECT FirstName, MidName, LastName FROM fullname WHERE Id=" + Integer.toString(rsPerson.getInt(2));
            rsFullname = stmt2.executeQuery(query);
            // Chu tai khoan
            if (!rsFullname.next())
                return null;
            chutaikhoan = rsFullname.getString(1) + " " + rsFullname.getString(2) + " " + rsFullname.getString(3);

            returnList.add(new SearchResult(makhoanvay, sotaikhoan, chutaikhoan, sotienvay));
        }
        return returnList;
    }

    public static PersonalDetails getPersonalDetails(SearchResult sr) throws SQLException {
        connection = getConnection();
        Statement stmt = connection.createStatement();
        PersonalDetails pd = new PersonalDetails();

        //ho ten
        pd.setHotenText(sr.getPersonname());

        //tai khoan
        String query = "SELECT Username FROM account WHERE Id = " + sr.getAccID();
        ResultSet rs1 = stmt.executeQuery(query);
        rs1.next();
        pd.setTaikhoanText(rs1.getString(1));

        //con lai
        query = "SELECT AddressId, CareerId, Gender, IdentityCard, Passport, PhoneNumber, DateObBirth FROM person WHERE AccountId = " + sr.getAccID();
        ResultSet rs2 = stmt.executeQuery(query);
        rs2.next();
        pd.setGioitinhText(rs2.getString(3));
        pd.setCmndText(Integer.toString(rs2.getInt(4)));
        pd.setHochieuText(Integer.toString(rs2.getInt(5)));
        pd.setDienthoaiText(Integer.toString(rs2.getInt(6)));

        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        pd.setNgaysinhText(df.format(rs2.getDate(7)));

        String AddressId = Integer.toString(rs2.getInt(1));
        String CareerId = Integer.toString(rs2.getInt(2));

        query = "SELECT NameOfAddress FROM address WHERE Id = " + AddressId;
        ResultSet rs3 = stmt.executeQuery(query);
        rs3.next();
        pd.setDiachiText(rs3.getString(1));

        query = "SELECT Organization FROM career WHERE Id = " + CareerId;
        ResultSet rs4 = stmt.executeQuery(query);
        rs4.next();
        pd.setCoquanText(rs4.getString(1));

        return pd;
	}

	public static ResultSet getResultSetPerson(String name, String gender, String identityCard, String birthOfDate,
			String address, String phoneNumber) throws SQLException {

		String query = "Select * from Person where Gender='" + gender + "'and IdentityCard='" + identityCard
				+ "'and DateObBirth='" + birthOfDate + "'and PhoneNumber='" + phoneNumber + "'";

		connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		int addressId = 0;

		int nameId = 0;

		if (!rs.next()) {
			return null;
		} else {

			return rs;

		}

	}

	public static ResultSet getResultSetPersonById(String identityCard) throws SQLException {

		String query = "Select * from Person where IdentityCard='" + identityCard + "'";

		connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		int addressId = 0;

		int nameId = 0;

		if (!rs.next()) {
			return null;
		} else {

			return rs;

		}

	}
	
	public static ResultSet getResultSetCareer(int id) throws SQLException {

		String query = "Select * from career where id='" + id + "'";

		connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		if (rs.next())
			return rs;

		return null;

	}

	public static ResultSet getRsLoan(String TypeLoan, String TypePurposeLoan, double MoneyLoan, String StartDateLoan,
			String dtEndDate) throws SQLException {

		LocalDate d1 = LocalDate.parse(StartDateLoan, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate d2 = LocalDate.parse(dtEndDate, DateTimeFormatter.ISO_LOCAL_DATE);

		Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());

		long diffdays = diff.toDays();

		System.out.println(diffdays);

		String statmentDate = (diffdays >= 365) ? "Hơn 12 tháng" : "Dưới 12 tháng";

		System.out.println(statmentDate);

		System.out.println(TypePurposeLoan);

		String query = "Select * from loan where (TypeOfLoan='" + TypeLoan + "' or TypeOfLoan is NULL)"
				+ "and loanPurpose='" + TypePurposeLoan + "' and timeOption='" + statmentDate + "'and (LoanMoneyLimit>="
				+ MoneyLoan + " OR LoanMoneyLimit=0)";

		System.out.println(query);

		connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()) {
			return rs;
		}

		return null;
	}

	public static int addRecordLoan(int loanId, int personId, double amountOfMoney, java.sql.Date startDate,
			java.sql.Date endDate, String PayOriginalDebtSchedule, String InterestPaySchedule, String PaymentMethod,
			String WithdrawalFundMethod) throws SQLException {

		String query = "Insert into recordloan (LoanId,PersonId,AmountOfMoney,startDate,endDate,"
				+ "PayOriginalDebtSchedule,InterestPaySchedule,PaymentMethod,WithdrawalFundMethod)  values(?,?,?,?,?,?,?,?,?)";

		connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

        connection.setAutoCommit(true);

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, loanId);

		ps.setInt(2, personId);

		ps.setDouble(3, amountOfMoney);

		ps.setDate(4, startDate);
		
		ps.setDate(5, endDate);

		ps.setString(6, PayOriginalDebtSchedule);

		ps.setString(7, InterestPaySchedule);

		ps.setString(8, PaymentMethod);

		ps.setString(9, WithdrawalFundMethod);

		int count = 0;
		count = ps.executeUpdate();

		return count;
	}

    public static String getFullName(int id) throws SQLException {

        String query = "Select * from Fullname where id=" + id + "";

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
        }

        return null;

    }

    public static String getAddress(int id) throws SQLException {
        String query = "Select * from Address where id=" + id + "";

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return rs.getString(2);
        }

        return null;
    }

}
