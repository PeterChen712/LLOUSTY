package llousty.Models;

public class Notif extends Model{
    private String title;
    private String text;
    private int userId;
    private String dateSent;
    private String type;
    public Notif(int id, String title, String text, int userId, String dateSent, String type) {
        super(id);
        this.title = title;
        this.text = text;
        this.userId = userId;
        this.dateSent = dateSent;
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}