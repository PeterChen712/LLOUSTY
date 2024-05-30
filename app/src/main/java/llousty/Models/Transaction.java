package llousty.Models;

public class Transaction extends Model{
    private int userId;
    private String date;
    private double totalPrice;
    private String payment;
    private String productName;
    private String seller;
    private String status;
    public Transaction(int id, int userId, String date, double totalPrice, String payment, String productName,
            String seller, String status) {
        super(id);
        this.userId = userId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.productName = productName;
        this.seller = seller;
        this.status = status;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}