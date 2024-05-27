package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Transaction;
import llousty.config.DbConfig;

public class TransactionController extends DbConfig{
    //CREATE
    public static boolean addTransaction(int productId, int userId, int stockPurchased, String date, double totalPrice, String payment){
        query = "INSERT INTO transaction (productId, userId, stockPurchased, date, totalPrice, payment VALUES (?, ?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, stockPurchased);
            preparedStatement.setString(4, date);
            preparedStatement.setDouble(5, totalPrice);
            preparedStatement.setString(6, payment);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ 
    public static List<Transaction> getAllTransactionByUserId(int userId){
        List<Transaction> transactions = new ArrayList<>();
        query = "SELECT * FROM transaction WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int productId = resultSet.getInt("productId");
                int stockPurchased = resultSet.getInt("stockPurchased");
                String date = resultSet.getString("date");
                double totalPrice = resultSet.getDouble("totalPrice");
                String payment = resultSet.getString("payment");
                Transaction transaction = new Transaction(id, productId, userId, stockPurchased, date, totalPrice, payment);
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
