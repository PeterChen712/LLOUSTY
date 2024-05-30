package llousty.controller;

import java.util.ArrayList;
import java.util.List;

import llousty.Models.Transaction;
import llousty.config.DbConfig;

public class TransactionController extends DbConfig{
    //CREATE
    public static boolean addTransaction(int userId, String date, double totalPrice, String payment, String productName, String seller, String status){
        query = "INSERT INTO transactions (userId, date, totalPrice, payment, productName, seller, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, date);
            preparedStatement.setDouble(3, totalPrice);
            preparedStatement.setString(4, payment);
            preparedStatement.setString(5, productName);
            preparedStatement.setString(6, seller);
            preparedStatement.setString(7, status);
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
        query = "SELECT * FROM transactions WHERE userId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                double totalPrice = resultSet.getDouble("totalPrice");
                String payment = resultSet.getString("payment");
                String productName = resultSet.getString("productName");
                String seller = resultSet.getString("seller");
                String status = resultSet.getString("status");
                Transaction transaction = new Transaction(id, userId, date, totalPrice, payment, productName, seller, status);
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    //DELETE
    public static boolean deleteTransaction(int transactionId){
        query = "DELETE FROM transactions WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, transactionId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
