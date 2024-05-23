package llousty.Models;

import java.io.InputStream;
public class User extends Model {
    String name, username, password, email, alamat, phone, gender;
    InputStream photoFile;
    
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

    public InputStream getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(InputStream photoFile) {
        this.photoFile = photoFile;
    }

    public User(int id, String name, String username, String password, String email, String alamat, String phone, String gender, InputStream photoFile) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.alamat = alamat;
        this.phone = phone;
        this.gender = gender;
        this.photoFile = photoFile;
    }

    
}
