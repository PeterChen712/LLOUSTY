package llousty.Models;


import javafx.scene.image.ImageView;

public class User extends Model {
    public User(int id) {
        super(id);
    }
    
    private String name, username, password, email, alamat, phone, gender;
    private ImageView photoFile;
    private String sellerMode;
    private int totalNotif;
    public User(int id, String name, String username, String password, String email, String alamat, String phone,
            String gender, ImageView photoFile, String sellerMode, int totalNotif) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.alamat = alamat;
        this.phone = phone;
        this.gender = gender;
        this.photoFile = photoFile;
        this.sellerMode = sellerMode;
        this.totalNotif = totalNotif;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public ImageView getPhotoFile() {
        return photoFile;
    }
    public void setPhotoFile(ImageView photoFile) {
        this.photoFile = photoFile;
    }
    public String getSellerMode() {
        return sellerMode;
    }
    public void setSellerMode(String sellerMode) {
        this.sellerMode = sellerMode;
    }
    public int getTotalNotif() {
        return totalNotif;
    }
    public void setTotalNotif(int totalNotif) {
        this.totalNotif = totalNotif;
    }
}