package llousty.Models;

public class Cart extends Model{
    int productId;
    int userId;
    String category;
    int totalAmount;
    double price;
    public Cart(int id, int productId, int userId, String category, int totalAmount, double price) {
        super(id);
        this.productId = productId;
        this.userId = userId;
        this.category = category;
        this.totalAmount = totalAmount;
        this.price = price;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
}