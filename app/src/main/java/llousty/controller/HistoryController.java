package llousty.controller;

import llousty.Models.History;
import llousty.config.DbConfig;
import java.util.List;
import java.util.ArrayList;

public class HistoryController extends DbConfig{
    
    //CREATE
    public static boolean createHistory(int sellerId, int customerId, String productName, int totalProduct, double totalPrice){
        query = "INSERT INTO history (sellerId, customerId, productName, totalProduct, totalPrice) VALUES (?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setString(3, productName);
            preparedStatement.setInt(4, totalProduct);
            preparedStatement.setDouble(5, totalPrice);
            preparedStatement.executeUpdate();
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<History> getAllHistoryBySellerId(int sellerId){
        List<History> histories = new ArrayList<>();
        query = "SELECT * FROM history WHERE sellerId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("customerId");
                String productName = resultSet.getString("productName");
                int totalProduct = resultSet.getInt("totalProduct");
                double totalPrice = resultSet.getDouble("totalPrice");
                History history = new History(id, sellerId, customerId, productName, totalProduct, totalPrice);
                histories.add(history);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return histories;
    }


    //DELETE
    public static boolean deleteHistoryById(int id){
        query = "DELETE FROM history WHERE id=?";
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