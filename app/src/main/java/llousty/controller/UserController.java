package llousty.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import llousty.config.DbConfig;

public class UserController extends DbConfig {
    public static boolean validasiLogin(String username, String password) {
        getConnection();
        query = "SELECT username, password FROM user WHERE username =? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet login = preparedStatement.executeQuery()) {
                return login.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean registAdd(String name, String username, String password, String email) {
        query = "INSERT INTO user (name,  username, password, email) VALUES (?,?,?,?)";

        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
