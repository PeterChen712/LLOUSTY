package llousty.Models;

public class History extends Model{
    int sellerId;
    int customerId;
    String productName;
    int totalProduct;
    double totalPrice;
    public History(int id, int sellerId, int customerId, String productName, int totalProduct, double totalPrice) {
        super(id);
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.productName = productName;
        this.totalProduct = totalProduct;
        this.totalPrice = totalPrice;
    }
    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getTotalProduct() {
        return totalProduct;
    }
    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}