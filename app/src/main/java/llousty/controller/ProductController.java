package llousty.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import llousty.Models.Product;
import llousty.config.DbConfig;

public class ProductController extends DbConfig{
    
    //CREATE
    public static boolean addProduct(String name, String description, String category, int stock, double price, File selectedFile, int sellerId){
        query = "INSERT INTO product (name, description, category, stock, price, productPhoto, sellerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, price);
            preparedStatement.setBinaryStream(6, new FileInputStream(selectedFile), (int) selectedFile.length());
            preparedStatement.setInt(7, sellerId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ
    public static List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        query = "SELECT * FROM product";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int stock = resultSet.getInt("stock");
                double rate = resultSet.getDouble("rate");
                double price = resultSet.getDouble("price");
                byte[] productPhotoByte = resultSet.getBytes("productPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                }
                else{
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                int totalRate = resultSet.getInt("totalRate");
                int sellerId = resultSet.getInt("sellerId");
                Product product = new Product(id, name, description, category, stock, rate, price, productPhoto, totalRate, sellerId);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    //READ
    public static Product getProductById(int id){
        Product product = null;
        query = "SELECT * FROM product WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int stock = resultSet.getInt("stock");
                double rate = resultSet.getDouble("rate");
                double price = resultSet.getDouble("price");
                byte[] productPhotoByte = resultSet.getBytes("productPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                }
                else{
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                int totalRate = resultSet.getInt("totalRate");
                int sellerId = resultSet.getInt("sellerId");
                product = new Product(id, name, description, category, stock, rate, price, productPhoto, totalRate, sellerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    //READ : for seller mode
    public static Product getProductBySellerId(int sellerId){
        Product product = null;
        query = "SELECT * FROM product WHERE sellerId=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int stock = resultSet.getInt("stock");
                double rate = resultSet.getDouble("rate");
                double price = resultSet.getDouble("price");
                byte[] productPhotoByte = resultSet.getBytes("productPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                }
                else{
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                int totalRate = resultSet.getInt("totalRate");
                product = new Product(id, name, description, category, stock, rate, price, productPhoto, totalRate, sellerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


    //READ : for seller mode get all product
    public static List<Product> getAllProductBySellerId(int sellerId) {
        List<Product> products = new ArrayList<>();
        query = "SELECT * FROM product WHERE sellerId = ?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int stock = resultSet.getInt("stock");
                double rate = resultSet.getDouble("rate");
                double price = resultSet.getDouble("price");
                byte[] productPhotoByte = resultSet.getBytes("productPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                } else {
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                int totalRate = resultSet.getInt("totalRate");
                Product product = new Product(id, name, description, category, stock, rate, price, productPhoto, totalRate, sellerId);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    //READ : for search
    public static Product getProductByName(String name){
        Product product = null;
        query = "SELECT * FROM product WHERE name=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                int stock = resultSet.getInt("stock");
                double rate = resultSet.getDouble("rate");
                double price = resultSet.getDouble("price");
                byte[] productPhotoByte = resultSet.getBytes("productPhoto");
                ImageView productPhoto;
                if (productPhotoByte != null) {
                    productPhoto = new ImageView(new Image(new ByteArrayInputStream(productPhotoByte)));
                }
                else{
                    productPhoto = new ImageView("/images/default/nullProduct.png");
                }
                int totalRate = resultSet.getInt("totalRate");
                int sellerId = resultSet.getInt("sellerId");
                product = new Product(id, name, description, category, stock, rate, price, productPhoto, totalRate, sellerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }



    //UPDATE
    public static boolean updateProduct(int id, String name, String description, String category, int stock, double rate, double price, File selectedFile, int totalRate, int sellerId){
        query = "UPDATE product SET name=?, description=?, category=?, stock=?, rate=?, price=?, productPhoto=?, totalRate=?, sellerId=? WHERE id=?";
        try {
            getConnection();
            System.out.println("hello");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, rate);
            preparedStatement.setDouble(6, price);
            preparedStatement.setBinaryStream(7, new FileInputStream(selectedFile), (int) selectedFile.length());
            preparedStatement.setInt(8, totalRate);
            preparedStatement.setInt(9, sellerId);
            preparedStatement.setInt(10, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //UPDATE
    public static boolean updateProductRating(int productId, double newRating, int totalRate) {
        String query = "UPDATE product SET rate = ?, totalRate = ? WHERE id = ?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, newRating);
            preparedStatement.setInt(2, totalRate);
            preparedStatement.setInt(3, productId);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //UPDATE
    public static boolean updateProductEdit(int id, String name, String description, String category, int stock, File selectedFile) {
        String query = "UPDATE product SET name=?, description=?, category=?, stock=?, productPhoto=? WHERE id=?";
        try {
            getConnection(); // Pastikan koneksi ke database telah terbuka
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, stock);
            preparedStatement.setBinaryStream(5, new FileInputStream(selectedFile), (int) selectedFile.length());
            preparedStatement.setInt(6, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //UPDATE
    public static boolean updateProductStock(int id, int stock){
        query = "UPDATE product SET stock=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //UPDATE
    public static boolean updateProductWithoutPhoto(int id, String name, String description, String category, int stock, double rate, double price, int totalRate, int sellerId){
        query = "UPDATE product SET name=?, description=?, category=?, stock=?, rate=?, price=?, totalRate=?, sellerId=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, rate);
            preparedStatement.setDouble(6, price);
            preparedStatement.setInt(7, totalRate);
            preparedStatement.setInt(8, sellerId);
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }





    //DELETE
    public static boolean deleteProduct(int id){
        query = "DELETE FROM product WHERE id=?";
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
