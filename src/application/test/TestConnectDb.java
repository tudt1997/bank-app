package application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.controllers.Providers;

class TestConnectDb {
	private Connection connection = Providers.getConnection();

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

			Providers.addRecordLoan(7, 1, 10500000, new Date(119, 2, 12), new Date(120, 2, 12), "Hàng quý", "Khác",
					"Tự động trừ tài khoản", "Tiền mặt");

			String query = "Select * from recordloan where LoanId=7 and PersonId=1 "
					+ "and AmountOfMoney=10500000 and startDate='2019-3-12' and "
					+ "endDate='2020-3-12' and PayOriginalDebtSchedule='Hàng quý' "
					+ "and InterestPaySchedule='Khác' and PaymentMethod='Tự động trừ tài khoản' "
					+ "and WithdrawalFundMethod='Tiền mặt'";

			// System.out.println(query);

			Statement stmt = Providers.connection.createStatement();

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
