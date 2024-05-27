package llousty.Models;

import javafx.scene.image.ImageView;

public class Product extends Model{
    private String name, description, category;
    private int stock;
    private double rate, price;
    private ImageView productPhoto;
    private int totalRate;
    private int sellerId;
    public Product(int id, String name, String description, String category, int stock, double rate, double price,
            ImageView productPhoto, int totalRate, int sellerId) {
        super(id);
        this.name = name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.rate = rate;
        this.price = price;
        this.productPhoto = productPhoto;
        this.totalRate = totalRate;
        this.sellerId = sellerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public ImageView getProductPhoto() {
        return productPhoto;
    }
    public void setProductPhoto(ImageView productPhoto) {
        this.productPhoto = productPhoto;
    }
    public int getTotalRate() {
        return totalRate;
    }
    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }
    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}