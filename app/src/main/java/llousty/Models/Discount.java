package llousty.Models;


public class Discount extends Model{
    private int discount;
    private int productId;
    public Discount(int id, int discount, int productId) {
        super(id);
        this.discount = discount;
        this.productId = productId;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}