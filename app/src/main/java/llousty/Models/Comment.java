package llousty.Models;

public class Comment extends Model{
    private String text;
    private int userId, productId, parentCommentId;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getParentCommentId() {
        return parentCommentId;
    }
    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
    
    public Comment(int id, String text, int userId, int productId, int parentCommentId) {
        super(id);
        this.text = text;
        this.userId = userId;
        this.productId = productId;
        this.parentCommentId = parentCommentId;
    }

    public Comment(int id, String text, int userId, int productId) {
        super(id);
        this.text = text;
        this.userId = userId;
        this.productId = productId;
    }
}
