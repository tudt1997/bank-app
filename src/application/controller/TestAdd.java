package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestAdd {

    public static Connection connection;

    private static String url = "jdbc:mysql://localhost:3306/test"
            + "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true?useUnicode=true&"
            + "characterEncoding=UTF-8&autoReconnect=true";

    private static String user = "root";

    private static String pass = "";

    public static void main(String[] args) {
        try {
            addRecordLoan(1, 1, 1000, new java.sql.Date(10000), new java.sql.Date(20000), "", "",
                    "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addRecordLoan(int loanId, int personId, double amountOfMoney, java.sql.Date startDate,
                                    java.sql.Date endDate, String PayOriginalDebtSchedule, String InterestPaySchedule, String PaymentMethod,
                                    String WithdrawalFundMethod) throws SQLException {

        String query = "Insert into recordloan (LoanId,PersonId,AmountOfMoney,startDate,endDate,"
                + "PayOriginalDebtSchedule,InterestPaySchedule,PaymentMethod,WithdrawalFundMethod)  values(?,?,?,?,?,?,?,?,?)";

        connection = getConnection();
//		connection = DriverManager.getConnection(url, user, pass);

        connection.setAutoCommit(false);

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

}
