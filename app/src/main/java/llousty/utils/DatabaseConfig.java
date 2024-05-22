package llousty.utils;


import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:sqlite:llousty.db";

    public static boolean insertData(String email, String username, String name, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS account " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
                "email TEXT NOT NULL UNIQUE, " +
                "username TEXT NOT NULL UNIQUE, " +
                "name TEXT NOT NULL, " +
                "password TEXT NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO account (email, username, name, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, name);
            pstmt.setString(4, password);

            try {
                if (email.isEmpty()) {
                    AlertNotif.setAlertWarning("Sign up Failed", null, "Please enter your email");
                    return false;
                }
                else if (name.isEmpty()) {
                    AlertNotif.setAlertWarning("Sign up Failed", null, "Please enter your name");
                    return false;
                }
                else if (username.isEmpty()) {
                    AlertNotif.setAlertWarning("Sign up Failed", null, "Please enter your username");
                    return false;
                    
                }
                else if (password.isEmpty()) {
                    AlertNotif.setAlertWarning("Sign up Failed", null, "Please enter your password");
                    return false;
                }
                else{
                    pstmt.executeUpdate();
                    System.out.println("Data inserted successfully!");
                    return true;
                }
            } catch (SQLException e) {
                if (e.getMessage().contains("UNIQUE constraint failed")) {
                    String constraint = e.getMessage().split(":")[1].trim();
                    if (constraint.contains("email")) {
                        AlertNotif.setAlertWarning("Sign up Failed", null, "Email already exists!");
                    } else if (constraint.contains("username")) {
                        AlertNotif.setAlertWarning("Sign up Failed", null, "Username already exists!");
                    }
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    public static boolean checkUsernamePassword(String username, String password) {
        if (username.isEmpty()) {
            AlertNotif.setAlertWarning("Login Failed", null, "Please enter your username");
            return false;
        } else if (password.isEmpty()) {
            AlertNotif.setAlertWarning("Login Failed", null, "Please enter your password");
            return false;
        } else {
            try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM account WHERE username=? AND password=?")) {

                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {

                    return true;
                } else {
                    AlertNotif.setAlertWarning("Login Failed", null, "Invalid username or password");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                AlertNotif.setAlertWarning("Login Failed", null, "Database error " + e.getMessage());
                return false;
            }
        }
    }
}