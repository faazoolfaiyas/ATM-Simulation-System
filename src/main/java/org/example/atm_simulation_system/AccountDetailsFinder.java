package org.example.atm_simulation_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetailsFinder {

    private static final String URL = "jdbc:postgresql://localhost:5432/atm_database";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "111111";

    // Method to find account details by card number and return them
    public static AccountData findDataByCardNumber(String cardNumber) {
        AccountData accountData = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // SQL query to retrieve data from both tables based on the card number
            String sql = "SELECT ad.id, ad.owner_name, ad.account_number, ad.amount, cd.card_number, cd.cvv, cd.pin, cd.pin_times\n" +
                    "FROM card_details cd\n" +
                    "JOIN account_details ad ON cd.account_id = ad.id\n" +
                    "WHERE cd.card_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cardNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve data from the result set
                String ownerName = rs.getString("owner_name");
                String accountNumber = rs.getString("account_number");
                double amount = rs.getDouble("amount");
                int cvv = rs.getInt("cvv");
                int pin = rs.getInt("pin");
                Integer pinTimes = rs.getInt("pin_times");

                // Create an instance of AccountData and assign retrieved values
                accountData = new AccountData(ownerName, accountNumber, amount, cvv, pin, pinTimes);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return accountData;
    }

    // Method to update the pin_times for a selected row using card_number
    public static void updatePinTimes(String cardNumber, int newPinTimes) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // SQL query to update pin_times based on card_number
            String sql = "UPDATE card_details SET pin_times = ? WHERE card_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newPinTimes);
            pstmt.setString(2, cardNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    // Method to update the pin for a selected row using card_number
    public static void update_new_pin(String cardNumber, int newPin) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // SQL query to update pin based on card_number
            String sql = "UPDATE card_details SET pin = ? WHERE card_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newPin);
            pstmt.setString(2, cardNumber);
            int rowsUpdated = pstmt.executeUpdate();

            //System.out.println(rowsUpdated + " row(s) updated."); // Debugging statement
        } catch (SQLException e) {
            System.err.println("Error updating pin: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public static void update_amount(String accountNumber, double amount) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // SQL query to update pin based on card_number
            String sql = "UPDATE account_details SET amount = ? WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            int rowsUpdated = pstmt.executeUpdate();

            //System.out.println(rowsUpdated + " row(s) updated."); // Debugging statement
        } catch (SQLException e) {
            System.err.println("Error updating pin: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}