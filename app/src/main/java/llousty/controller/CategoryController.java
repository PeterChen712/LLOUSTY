package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Category;
import llousty.config.DbConfig;

public class CategoryController extends DbConfig{
    
    //CREATE
    public static boolean addCategory(String category, int productId) {
        String query = "INSERT INTO category (category, productId) VALUES (?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ: for product scene
    public static List<Category> getAllCategoryByProductId(int productId) {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                Category categoryResult = new Category(id, category, productId);
                categories.add(categoryResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    //READ: for cart scene
    public static Category getCategoryById(int id) {
        Category category = null;
        String query = "SELECT * FROM category WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("category");
                int productId = resultSet.getInt("productId");
                category = new Category(id, categoryName, productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
