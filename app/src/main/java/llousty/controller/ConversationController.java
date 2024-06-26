package llousty.controller;

import java.io.FileNotFoundException;
import llousty.Models.Conversation;
import llousty.config.DbConfig;

public class ConversationController extends DbConfig{
    //CREATE
    public static boolean addConversation(String participantsId, String messageIdList){
        query = "INSERT INTO conversation (participantsId, messageIdList) VALUES (?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, participantsId);
            preparedStatement.setString(2, messageIdList);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //READ
    public static Conversation getConversationByParticipantsId(String participantsId){
        Conversation conversation = null;
        query = "SELECT * FROM conversation WHERE participantsId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, participantsId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String messageIdList = resultSet.getString("messageIdList");
                conversation = new Conversation(id, participantsId, messageIdList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversation;
    }


    // UPDATE
    // public static boolean updateConversation(int id, String participantsId, String messageIdList) throws FileNotFoundException {
    //     query = "UPDATE conversation SET participantsId=?, messageIdList=? WHERE id=?";
    //     try {
    //         getConnection();
    //         preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, participantsId);
    //         preparedStatement.setString(2, messageIdList);
    //         preparedStatement.setInt(3, id);
    //         int rowsUpdated = preparedStatement.executeUpdate();
    //         return rowsUpdated > 0;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
        
    //     return false;
    // }

    public static boolean updateConversation(int id, String participantsId, String messageIdList) throws FileNotFoundException {
        query = "UPDATE conversation SET participantsId=?, messageIdList=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, participantsId);
            preparedStatement.setString(2, messageIdList);
            preparedStatement.setInt(3, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // public static boolean updateConversation(int id, String participantsId, String messageIdList) throws FileNotFoundException {
    //     synchronized (lock) {
    //         try {
    //             String query = "UPDATE conversation SET participantsId=?, messageIdList=? WHERE id=?";
    //             getConnection();
    //             PreparedStatement preparedStatement = connection.prepareStatement(query);
    //             preparedStatement.setString(1, participantsId);
    //             preparedStatement.setString(2, messageIdList);
    //             preparedStatement.setInt(3, id);
    //             int rowsUpdated = preparedStatement.executeUpdate();
    //             return rowsUpdated > 0;
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return false;
    // }


    //DELETE
    public static boolean deleteConversationById(int conversationId){
        query = "DELETE FROM conversation WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, conversationId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
