package llousty.controller;

import llousty.config.DbConfig;
import llousty.Models.Cart;
import java.util.List;
import java.util.ArrayList;



public class CartController extends DbConfig{
    //CREATE
    public static boolean addCart(int productId, int userId, String variantType, int totalAmount, double price){
        query = "INSERT INTO comment (productId, userId, variantType, totalAmount, price) VALUES (?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, variantType);
            preparedStatement.setInt(4, totalAmount);
            preparedStatement.setDouble(5, price);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<Cart> getAllCartByUserId(int userId){
        List<Cart> carts = new ArrayList<>();
        query = "SELECT * FROM cart WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int productId = resultSet.getInt("productId");
                String variantType = resultSet.getString("variantType");
                int totalAmount = resultSet.getInt("totalAmount");
                double price = resultSet.getDouble("price");
                Cart cart = new Cart(id, productId, userId, variantType, totalAmount, price);
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }
}
