package llousty.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import llousty.Models.Product;
import llousty.Models.Variant;
import llousty.config.DbConfig;

public class VariantController extends DbConfig{
    
    //CREATE
    public static boolean addVariant(String variantName, int productId, File selectedFile){
        query = "INSERT INTO variant (variant, productId, variantPhoto) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, variantName);
            preparedStatement.setInt(2, productId);
            preparedStatement.setBinaryStream(3, new FileInputStream(selectedFile), (int) selectedFile.length());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ: for product scene
    public static List<Variant> getAllVariantByProductId(int productId){
        List<Variant> variants = new ArrayList<>();
        query = "SELECT * FROM variant WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String variant = resultSet.getString("variantName");
                byte[] variantPhotoByte = resultSet.getBytes("variantPhoto");
                ImageView variantPhoto;
                if (variantPhotoByte != null) {
                    variantPhoto = new ImageView(new Image(new ByteArrayInputStream(variantPhotoByte)));
                }
                else{
                    variantPhoto = new ImageView("/images/default/nullProduct.png");
                }
                Variant varianResult = new Variant(id, variant, productId, variantPhoto);
                variants.add(varianResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variants;
    }

    //READ : for cart scene
    public static Variant getVariantByProductId(int productId){
        Variant variant = null;
        query = "SELECT * FROM variant WHERE productId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String variantName = resultSet.getString("variantName");
                byte[] productPhotoByte = resultSet.getBytes("variantPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                }
                else{
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                variant = new Variant(id, variantName, productId, productPhoto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variant;
    }
}
