package llousty.Models;

public class Cart extends Model{
    int productId;
    int userId;
    String variantType;
    int totalAmount;
    double price;
    
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

    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variantType) {
        this.variantType = variantType;
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

    public Cart(int id, int productId, int userId, String variantType, int totalAmount, double price) {
        super(id);
        this.productId = productId;
        this.userId = userId;
        this.variantType = variantType;
        this.totalAmount = totalAmount;
        this.price = price;
    }
}
