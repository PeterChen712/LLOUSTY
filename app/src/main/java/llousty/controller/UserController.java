package llousty.controller;


import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import llousty.Models.User;
import llousty.Utils.PasswordHasher;
import llousty.config.DbConfig;
import java.io.ByteArrayInputStream;


public class UserController extends DbConfig {
    //READ
    public static boolean validasiLogin(String username, String password) {
        getConnection();
        query = "SELECT username, password FROM user WHERE username =? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PasswordHasher.doHashing(password)); // menghasing inputan dan memngecek apakah sama di database
            try (ResultSet login = preparedStatement.executeQuery()) {
                return login.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    //CREATE
    public static boolean registAdd(String name, String username, String password, String email) {
        query = "INSERT INTO user (name,  username, password, email) VALUES (?,?,?,?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, PasswordHasher.doHashing(password));// password yang masuk ke database di hashing
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //CREATE
    public static boolean updateUser(int id, String name, String username, String password, String email, String alamat, String phone, String gender, InputStream photoFile) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
    
        if (selectedFile != null) {
            try {
                getConnection();
                String query = "UPDATE user SET name=?, username=?, password=?, email=?, alamat=?, phone=?, gender=?, photo_profile=? WHERE id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, alamat);
                preparedStatement.setString(6, phone);
                preparedStatement.setString(7, gender);
                preparedStatement.setBinaryStream(8, photoFile);
                preparedStatement.setInt(9, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                return rowsUpdated > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //READ
    public static User showUserProfile(int id) throws SQLException {
        User user = null;
        query = "SELECT * FROM user WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String alamat = resultSet.getString("alamat");
                String phone = resultSet.getString("phone");
                String gender = resultSet.getString("gender");
                byte[] photoProfile = resultSet.getBytes("photo_profile");
                user = new User(id, name, username, password, email, alamat, phone, gender, new ByteArrayInputStream(photoProfile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
