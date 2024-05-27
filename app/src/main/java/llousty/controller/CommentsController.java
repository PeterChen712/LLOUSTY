package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Comment;
import llousty.config.DbConfig;

public class CommentsController extends DbConfig{
    //CREATE
    public static boolean addComment(String text, int userId, int productId, int parentCommentId){
        query = "INSERT INTO comment (text, userId, productId, parentCommentId) VALUES (?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, productId);
            preparedStatement.setInt(4, parentCommentId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<Comment> getAllCommentByProductId(int productId){
        List<Comment> comments = new ArrayList<>();
        query = "SELECT * FROM comment WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                int userId = resultSet.getInt("userId");
                int parentCommentId = resultSet.getInt("parentCommentId");
                Comment comment = new Comment(id, text, userId, productId, parentCommentId);
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
