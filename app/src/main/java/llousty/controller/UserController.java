package llousty.controller;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import llousty.Models.User;
import llousty.Utils.PasswordHasher;
import llousty.config.DbConfig;
import java.io.ByteArrayInputStream;

public class UserController extends DbConfig {
    // READ
    public static User validasiLogin(String username, String password) {
        getConnection();
        query = "SELECT * FROM user WHERE username =? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PasswordHasher.doHashing(password));
            try (ResultSet login = preparedStatement.executeQuery()) {
                if (login.next()) {
                    int id = login.getInt("id");
                    User user = new User(id);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ USERNAME OR EMAIL THAT THEY ALREADY TAKEN
    public static boolean isUsernameOrEmailTaken(String username, String email) {
        query = "SELECT COUNT(*) FROM user WHERE username = ? OR email = ?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // CREATE
    public static boolean registAdd(String name, String username, String password, String email) {
        query = "INSERT INTO user (name,  username, password, email, sellerMode) VALUES (?,?,?,?,?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, PasswordHasher.doHashing(password));// password yang masuk ke database di
                                                                               // hashing
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, "User");
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // UPDATE
    public static boolean updateUser(int id, String name, String username, String password, String email, String alamat,
            String phone, String gender, File selectedFile, String sellerMode, int totalNotif, String listChatId) throws FileNotFoundException {

        if (selectedFile != null) {
            try {
                getConnection();
                String query = "UPDATE user SET name=?, username=?, password=?, email=?, alamat=?, phone=?, gender=?, photo_profile=?, sellerMode=?, totalNotif=?, listChatId=? WHERE id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, alamat);
                preparedStatement.setString(6, phone);
                preparedStatement.setString(7, gender);
                preparedStatement.setBinaryStream(8, new FileInputStream(selectedFile), (int) selectedFile.length());
                preparedStatement.setString(9, sellerMode);
                preparedStatement.setInt(10, totalNotif);
                preparedStatement.setString(11, listChatId);
                preparedStatement.setInt(12, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                return rowsUpdated > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // TES
        }
        return false;
    }

    // UPDATE
    public static boolean updateUserMyProfile(int id, String name, String username, String password, String email,
            String alamat, String phone, String gender) throws FileNotFoundException {
        try {
            getConnection();
            String query = "UPDATE user SET name=?, username=?, password=?, email=?, alamat=?, phone=?, gender=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, alamat);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, gender);
            preparedStatement.setInt(8, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //UPDATE
    public static boolean updateUserListChatId(int id, String listChatId) {
        try {
            getConnection();
            String query = "UPDATE user SET listChatId=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, listChatId);
            preparedStatement.setInt(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //UPDATE
    public static boolean updateTotalNotif(int id, int totalNotif) {
        try {
            getConnection();
            String query = "UPDATE user SET totalNotif = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, totalNotif);
            preparedStatement.setInt(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // UPDATE
    public static boolean updateUserSellerMode(int id, String sellerMode) {
        try {
            getConnection();
            String query = "UPDATE user SET sellerMode=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sellerMode);
            preparedStatement.setInt(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    

    // READ
    public static User getUserById(int id) throws SQLException {
        User user = null;
        query = "SELECT * FROM user WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                while (userResultSet.next()) {
                    String name = userResultSet.getString("name");
                    String username = userResultSet.getString("username");
                    String password = userResultSet.getString("password");
                    String email = userResultSet.getString("email");
                    String alamat = userResultSet.getString("alamat");
                    String phone = userResultSet.getString("phone");
                    String gender = userResultSet.getString("gender");
                    byte[] photoProfileByte = userResultSet.getBytes("photo_profile");
                    ImageView photoProfile;
                    if (photoProfileByte != null) {
                        photoProfile = new ImageView(new Image(new ByteArrayInputStream(photoProfileByte)));
                    } else {
                        photoProfile = new ImageView("/images/default/nullProfile.jpg");
                    }
                    String sellerMode = userResultSet.getString("sellerMode");
                    int totalNotif = userResultSet.getInt("totalNotif");
                    String listChatId = userResultSet.getString("listChatId");
                    user = new User(id, name, username, password, email, alamat, phone, gender, photoProfile, sellerMode, totalNotif, listChatId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // READ
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        query = "SELECT * FROM user";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String alamat = resultSet.getString("alamat");
                String phone = resultSet.getString("phone");
                String gender = resultSet.getString("gender");
                byte[] photoProfileByte = resultSet.getBytes("photo_profile");
                ImageView photoProfile;
                if (photoProfileByte != null) {
                    photoProfile = new ImageView(new Image(new ByteArrayInputStream(photoProfileByte)));
                } else {
                    photoProfile = new ImageView("/images/default/nullProfile.jpg");
                }
                String sellerMode = resultSet.getString("sellerMode");
                int totalNotif = resultSet.getInt("totalNotif");
                String listChatId = resultSet.getString("listChatId");
                User user = new User(id, name, username, password, email, alamat, phone, gender, photoProfile, sellerMode, totalNotif, listChatId);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    //READ
    public static User getUserByName(String name) {
        User user = null;
        query = "SELECT * FROM user WHERE name=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                while (userResultSet.next()) {
                    int id = userResultSet.getInt("id");
                    String username = userResultSet.getString("username");
                    String password = userResultSet.getString("password");
                    String email = userResultSet.getString("email");
                    String alamat = userResultSet.getString("alamat");
                    String phone = userResultSet.getString("phone");
                    String gender = userResultSet.getString("gender");
                    byte[] photoProfileByte = userResultSet.getBytes("photo_profile");
                    ImageView photoProfile;
                    if (photoProfileByte != null) {
                        photoProfile = new ImageView(new Image(new ByteArrayInputStream(photoProfileByte)));
                    } else {
                        photoProfile = new ImageView("/images/default/nullProfile.jpg");
                    }
                    String sellerMode = userResultSet.getString("sellerMode");
                    int totalNotif = userResultSet.getInt("totalNotif");
                    String listChatId = userResultSet.getString("listChatId");
                    user = new User(id, name, username, password, email, alamat, phone, gender, photoProfile, sellerMode, totalNotif, listChatId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
