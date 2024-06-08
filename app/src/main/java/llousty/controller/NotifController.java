package llousty.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import llousty.Models.Notif;
import llousty.config.DbConfig;

public class NotifController extends DbConfig{
     //CREATE
    public static boolean addNotif(String title, String text, int userId, String dateSent, String type){
        query = "INSERT INTO notif (title, text, userId, dateSent, type) VALUES (?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, userId);
            preparedStatement.setString(4, dateSent);
            preparedStatement.setString(5, type);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<Notif> getAllNotifByUserId(int userId){
        List<Notif> notifs = new ArrayList<>();
        query = "SELECT * FROM notif WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                String dateSent = resultSet.getString("dateSent");
                String type = resultSet.getString("type");
                Notif notif = new Notif(id, title, text, userId, dateSent, type);
                notifs.add(notif);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifs;
    }


    //UPDATE
    public static boolean updateNotif(int id, String title, String text, int userId, String dateSent, String type) throws FileNotFoundException {
        query = "UPDATE conversation SET title=?, text=?, userId=?, dateSent=?, type=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, userId);
            preparedStatement.setString(4, dateSent);
            preparedStatement.setString(5, type);
            preparedStatement.setInt(7, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //DELETE
    public static boolean deleteNotif(int id){
        query = "DELETE FROM notif WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
