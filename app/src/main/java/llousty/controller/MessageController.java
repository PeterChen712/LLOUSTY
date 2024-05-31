package llousty.controller;

import java.sql.ResultSet;
import llousty.Models.Message;
import llousty.config.DbConfig;

public class MessageController extends DbConfig {
    // CREATE
    public static boolean addMessage(int senderId, int targetId, String text) {
        query = "INSERT INTO message (senderId, targetId, text) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, targetId);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
            // preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // READ
    public static Message getMessageByMessageId(int messageId) {
        Message message = null;
        query = "SELECT * FROM message WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, messageId);
            try (ResultSet commentResultSet = preparedStatement.executeQuery()) {
                while (commentResultSet.next()) {
                    int senderId = commentResultSet.getInt("senderId");
                    int targetId = commentResultSet.getInt("targetId");
                    String text = commentResultSet.getString("text");
                    message = new Message(messageId, senderId, targetId, text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    // READ for add in list massage in conversation db
    public static int getMessageId(int userId, int targetId, String text) {
        int messageId = 0;
        query = "SELECT id FROM message WHERE senderId=? AND targetId=? AND text=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(1, targetId);
            preparedStatement.setString(3, text);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                messageId = id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageId;
    }

    // READ the last
    public static int getLatestMessageId() {
        int latestId = 0;
        String query = "SELECT MAX(id) AS latest_id FROM message";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            try (ResultSet messagResultSet = preparedStatement.executeQuery()) {
                if (messagResultSet.next()) {
                    latestId = messagResultSet.getInt("latest_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestId;
    }

}
