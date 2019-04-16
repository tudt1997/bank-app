package application.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.controller.Providers;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestConnectDb {
	private Connection connection = Providers.getConnection();

	public int loanId = 7;

	public int personId = 1;

	public double amountOfMoney = 10500000;

	public String startDate = "2019-03-12";

	public String[] startDateArr = startDate.split("-");

	public String endDate = "2020-03-12";

	public String[] endDateArr = endDate.split("-");

	public String payOriginalDebtSchedule = "Hàng quý";

	public String interestPaySchedule = "Khác";

	public String paymentMethod = "Tự động trừ tài khoản";

	public String withdrawalFundMethod = "Tiền mặt";

	@BeforeEach
	private void setUp() {

		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterEach
	private void tearDown() {
		try {
			connection.rollback();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testLoginSuccess() {

		boolean flag = false;
		try {

			flag = Providers.checkLogin("chinhvu", "123");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(true, flag);

	}

	@Test
	void testLoginFail1() {

		boolean flag = false;
		try {

			flag = Providers.checkLogin("chinhvu", "1234");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(false, flag);

	}

	@Test
	void testAddRecord() {

		try {

			Providers.connection.setAutoCommit(false);

			System.out.println("Check date: " + startDateArr[1]);

			Providers.addRecordLoan(loanId, personId, amountOfMoney,
					new Date(Integer.valueOf(startDateArr[0]) - 1900, Integer.valueOf(startDateArr[1]) - 1,
							Integer.valueOf(startDateArr[2])),
					new Date(120, 2, 12), payOriginalDebtSchedule, interestPaySchedule, paymentMethod,
					withdrawalFundMethod);

			String query = "Select * from recordloan where LoanId=" + loanId + " and PersonId=" + personId + " "
					+ "and AmountOfMoney='" + amountOfMoney + "' and startDate='" + startDate + "' and " + "endDate='"
					+ endDate + "' and PayOriginalDebtSchedule='" + payOriginalDebtSchedule + "'"
					+ " and InterestPaySchedule='" + interestPaySchedule + "' and PaymentMethod='" + paymentMethod + "'"
					+ " and WithdrawalFundMethod='" + withdrawalFundMethod + "'";
			// System.out.println(query);

			Statement stmt = Providers.connection.createStatement();
			
//			String query2 = "Select * from recordloan where LoanId=? and PersonId=? "
//					+ "and AmountOfMoney='?' and startDate='?' and " + "endDate='?' and PayOriginalDebtSchedule='?'"
//					+ " and InterestPaySchedule='?' and PaymentMethod='?'" + " and WithdrawalFundMethod='?'";
//			PreparedStatement ps = connection.prepareStatement(query2);
//			ps.setInt(1, loanId);
//			ps.setInt(2, personId);
//			ps.setDouble(3, amountOfMoney);
//			ps.setString(4, startDate);
//			ps.setString(5, endDate);
//			ps.setString(6, payOriginalDebtSchedule);
//			ps.setString(7, interestPaySchedule);
//			ps.setString(8, paymentMethod);
//			ps.setString(9, withdrawalFundMethod);
			
			ResultSet rs = stmt.executeQuery(query);

			assertEquals(true, rs.next());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				Providers.connection.rollback();
//				Providers.connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Test
	void checkaddTrueData() {
		try {
			Providers.connection.setAutoCommit(false);

			Providers.addRecordLoan(7, 1, 10500000, new Date(119, 2, 12), new Date(120, 2, 12), payOriginalDebtSchedule, interestPaySchedule,
					paymentMethod, withdrawalFundMethod);

			String query = "Select * from recordloan where LoanId=" + loanId + " and PersonId=" + personId + " "
					+ "and AmountOfMoney='" + amountOfMoney + "' and startDate='" + startDate + "' and " + "endDate='"
					+ endDate + "' and PayOriginalDebtSchedule='" + payOriginalDebtSchedule + "'"
					+ " and InterestPaySchedule='" + interestPaySchedule + "' and PaymentMethod='" + paymentMethod + "'"
					+ " and WithdrawalFundMethod='" + withdrawalFundMethod + "'";

			// System.out.println(query);

			Statement stmt = Providers.connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			String query1 = "select * from recordloan";

			Statement stmt1 = Providers.connection.createStatement();

			ResultSet rs1 = stmt1.executeQuery(query1);

			rs1.next();

			if (rs1.getInt("LoanId") != loanId)
				assertEquals(true, false);

			if (rs1.getInt("PersonId") != personId)
				assertEquals(true, false);

			if (rs1.getDouble("AmountOfMoney") != amountOfMoney)
				assertEquals(true, false);

			System.out.println("Date: " + rs1.getString("startDate").toString());

			if (!rs1.getString("startDate").toString().equals(startDate))
				assertEquals(true, false);

			if (!rs1.getString("endDate").toString().equals(endDate))
				assertEquals(true, false);

			if (!rs1.getString("PayOriginalDebtSchedule").equals(payOriginalDebtSchedule))
				assertEquals(true, false);

			if (!rs1.getString("InterestPaySchedule").equals(interestPaySchedule))
				assertEquals(true, false);

			if (!rs1.getString("PaymentMethod").equals(paymentMethod))
				assertEquals(true, false);

			if (!rs1.getString("WithdrawalFundMethod").equals(withdrawalFundMethod))
				assertEquals(true, false);

			assertEquals(true, true);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				Providers.connection.rollback();

//				Providers.connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Test
	void getRsLoan() {
		try {
			ResultSet rs = Providers.getRsLoan("Vay theo món", "Vay tiêu dùng không tài sản đảm bảo", 1000000,
					new Date(119, 3, 10).toString(), new Date(120, 3, 24).toString());

			int count = 0;

			do {
				count++;
			} while (rs.next());

			System.out.println(count);

			assertEquals(1, count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void getResultSetCareer() {
		try {
			ResultSet rs = Providers.getResultSetCareer(1);

			int count = 0;

			do {
				count++;
			} while (rs.next());

			assertEquals(1, count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void getResultSetPerson() {
		try {
			ResultSet rs = Providers.getResultSetPerson("Nguyễn Đức Anh", "Male", "123456",
					new Date(119, 2, 11).toString(), "376 Thụy Khuê Hà Nội", "123456789");

			int count = 0;

			do {
				count++;
			} while (rs.next());

			assertEquals(1, count);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkNameBaseName1() {

		try {
			assertEquals(true, Providers.checkNameBaseName(1, "Nguyễn Đức Anh"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkNameBaseName2() {

		try {
			assertEquals(false, Providers.checkNameBaseName(1, "Doãn Tuấn Tú"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkAddressBaseId1() {

		try {
			assertEquals(true, Providers.checkAddressBaseId(1, "42 Trần Phú Hà Đông Hà Nội"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkAddressBaseId2() {

		try {
			assertEquals(false, Providers.checkAddressBaseId(1, "376 Thụy Khuê Hà Nội"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkPersonAvailable1() {

		try {

			assertEquals(true, Providers.checkPersonAvailable("Nguyễn Đức Anh", "Male", "123456",
					new Date(119, 2, 11).toString(), "42 Trần Phú Hà Đông Hà Nội", "123456789"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void checkPersonAvailable2() {

		try {

			assertEquals(false, Providers.checkPersonAvailable("Doãn Tuấn Tú", "Male", "234567",
					new Date(1997, 11, 26).toString(), "376 Thụy Khuê Hà Nội", "234567890"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
