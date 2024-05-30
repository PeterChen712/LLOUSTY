package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Chat;
import llousty.config.DbConfig;

public class ChatController extends DbConfig{
     //CREATE
    public static boolean addChat(String text, int targetUserId, int userId){
        query = "INSERT INTO chatDB (text, targetUserId, userId) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, targetUserId);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<Chat> getAllChatById(int chatId){
        List<Chat> chats = new ArrayList<>();
        query = "SELECT * FROM chatDB WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chatId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String text = resultSet.getString("text");
                int targetUserId = resultSet.getInt("targetUserId");
                int userId = resultSet.getInt("userId");
                Chat chat = new Chat(chatId, text, targetUserId, userId);
                chats.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    //READ
    public static List<Chat> getAllChatByTargetId(int targetId){
        List<Chat> chats = new ArrayList<>();
        query = "SELECT * FROM chatDB WHERE targetUserId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, targetId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                int userId = resultSet.getInt("userId");
                Chat chat = new Chat(id, text, targetId, userId);
                chats.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }


}
