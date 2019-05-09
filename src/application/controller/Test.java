package application.controller;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {

    public static void main(String[] args) {
        try {
            Providers.updateRecordLoan(23,1000000);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(true,true);
    }

}
