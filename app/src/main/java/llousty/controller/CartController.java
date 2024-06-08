package llousty.controller;
import llousty.config.DbConfig;
import llousty.Models.Cart;
import java.util.List;
import java.util.ArrayList;
public class CartController extends DbConfig{
    //CREATE
    public static boolean addCart(int productId, int userId, String category, int totalAmount, double price){
        query = "INSERT INTO cart (productId, userId, category, totalAmount, price) VALUES (?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, category);
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
                String category = resultSet.getString("category");
                int totalAmount = resultSet.getInt("totalAmount");
                double price = resultSet.getDouble("price");
                Cart cart = new Cart(id, productId, userId, category, totalAmount, price);
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }

    //READ 
    public static Cart getCartById(int id){
        Cart cart = null;
        query = "SELECT * FROM cart WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                int userId = resultSet.getInt("userId");
                String category = resultSet.getString("category");
                int totalAmount = resultSet.getInt("totalAmount");
                double price = resultSet.getDouble("price");
                cart = new Cart(id, productId, userId, category, totalAmount, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    //READ
    public static Cart getCartByUserId(int userId){
        Cart cart = null;
        query = "SELECT * FROM cart WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int productId = resultSet.getInt("productId");
                String category = resultSet.getString("category");
                int totalAmount = resultSet.getInt("totalAmount");
                double price = resultSet.getDouble("price");
                cart = new Cart(id, productId, userId, category, totalAmount, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    //DELETE
    public static boolean deleteCartByProductId(int productId){
        query = "DELETE FROM cart WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //DELETE
    public static boolean deleteCartById(int id){
        query = "DELETE FROM cart WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}