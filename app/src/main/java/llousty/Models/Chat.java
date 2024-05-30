package llousty.Models;

public class Chat extends Model{
    private String text;
    private int targetUserId;
    private int userId;
    public Chat(int id, String text, int targetUserId, int userId) {
        super(id);
        this.text = text;
        this.targetUserId = targetUserId;
        this.userId = userId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getTargetUserId() {
        return targetUserId;
    }
    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}