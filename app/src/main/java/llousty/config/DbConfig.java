package llousty.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConfig {
    static final String DB_URL = "jdbc:sqlite:src/main/resources/db/db_user.db";

    protected static Connection connection;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet resultSet;
    protected static String query;

    public static void getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("BERHASIL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public static boolean validasiLogin(String username, String password) {
    // getConnection();
    // query = "SELECT username, password FROM user WHERE username =? AND
    // password=?";
    // try {
    // preparedStatement = connection.prepareStatement(query);
    // preparedStatement.setString(1, username);
    // preparedStatement.setString(2, password);
    // try (ResultSet login = preparedStatement.executeQuery()) {
    // return login.next();
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return false;

    // }

}
