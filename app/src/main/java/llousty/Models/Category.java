package llousty.Models;


public class Category extends Model{
    private String category;
    private int productId;
    public Category(int id, String category, int productId) {
        super(id);
        this.category = category;
        this.productId = productId;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}