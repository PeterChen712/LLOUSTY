package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Chat;
import llousty.Models.Notif;
import llousty.config.DbConfig;

public class NotifController extends DbConfig{
     //CREATE
    public static boolean addNotif(String text, int userId, String dateSent, int cartId, int chatId, int productId){
        query = "INSERT INTO notif (text, userId, dateSent, cartId, chatId, productId) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, dateSent);
            preparedStatement.setInt(4, cartId);
            preparedStatement.setInt(5, chatId);
            preparedStatement.setInt(6, productId);
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
                String text = resultSet.getString("text");
                String dateSent = resultSet.getString("dateSent");
                int cartId = resultSet.getInt("cartId");
                int chatId = resultSet.getInt("chatId");
                int productId = resultSet.getInt("productId");
                Notif notif = new Notif(id, text, userId, dateSent, cartId, chatId, productId);
                notifs.add(notif);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifs;
    }

}
