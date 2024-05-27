package llousty.Models;

public class Transaction extends Model{
    private int productId;
    private int userId;
    private int stockPurchased;
    private String date;
    private double totalPrice;
    private String payment;
    
    public Transaction(int id, int productId, int userId, int stockPurchased, String date, double totalPrice,
            String payment) {
        super(id);
        this.productId = productId;
        this.userId = userId;
        this.stockPurchased = stockPurchased;
        this.date = date;
        this.totalPrice = totalPrice;
        this.payment = payment;
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
    public int getStockPurchased() {
        return stockPurchased;
    }
    public void setStockPurchased(int stockPurchased) {
        this.stockPurchased = stockPurchased;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    

}
