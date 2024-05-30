package llousty.Models;

public class Message extends Model{
    private int senderId;
    private int targetId;
    private String text;

    public Message(int id) {
        super(id);
    }

    public Message(int id, int senderId, int targetId, String text) {
        super(id);
        this.senderId = senderId;
        this.targetId = targetId;
        this.text = text;
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getTargetId() {
        return targetId;
    }
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    };
    
}
