package llousty.Models;

public class Notif extends Model{
    private String text;
    private int userId;
    private String dateSent;
    private int cartId;
    private int chatId;
    private int productId;
    public Notif(int id, String text, int userId, String dateSent, int cartId, int chatId, int productId) {
        super(id);
        this.text = text;
        this.userId = userId;
        this.dateSent = dateSent;
        this.cartId = cartId;
        this.chatId = chatId;
        this.productId = productId;
    }
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
    public String getDateSent() {
        return dateSent;
    }
    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public int getChatId() {
        return chatId;
    }
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}