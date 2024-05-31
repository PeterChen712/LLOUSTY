package llousty.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import llousty.Models.Discount;
import llousty.config.DbConfig;

public class DiscountController extends DbConfig{
    
    //CREATE
    public static boolean addDiscount(int discount, int productId){
        query = "INSERT INTO discount (discount, productId) VALUES (?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ: for product scene
    public static List<Discount> getAllDiscountByProductId(int productId){
        List<Discount> discounts = new ArrayList<>();
        query = "SELECT * FROM discount WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int discount = resultSet.getInt("discount");
                Discount discountResult = new Discount(id, discount, productId);
                discounts.add(discountResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discounts;
    }

    //READ : for cart scene
    public static Discount getDiscountById(int id){
        Discount discount = null;
        query = "SELECT * FROM discount WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int discountTotal = resultSet.getInt("discount");
                int productId = resultSet.getInt("productId");
                discount = new Discount(id, discountTotal, productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount;
    }
}
