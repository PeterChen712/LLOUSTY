package llousty.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.User;
import llousty.Utils.PasswordHasher;
import llousty.Utils.imageSet;
import llousty.Utils.photoUploader;
import llousty.components.Navbar;
import llousty.controller.UserController;

public class ProfileScene {
    // private boolean sellerMode = false;
    private static Stage stage;
    private File selectedFile;

    public ProfileScene(Stage stage) {
        this.stage = stage;
    }

    private void showSellerMode(int userId, StackPane sisiKiriProfileScene) throws SQLException {
        
        
        
        //MAIN 
        
        VBox navbar = Navbar.getNavbarSeller(stage, userId);
        Scene scene = new Scene(navbar, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    public void show(int userId) throws SQLException {
        User user = UserController.getUserById(userId);
        ImageView fotoProfil = imageSet.setImages(user.getPhotoFile(), 103, 98);
        ImageView borderPict = imageSet.setImages("/images/product/border.png", 103, 98);
        StackPane fotoPane = new StackPane(fotoProfil, borderPict);
        fotoPane.setAlignment(Pos.CENTER);
        Label MyProfile = new Label("My Profile");
        Label SellerMode = new Label("Seller Mode");
        Label Logout = new Label("Logout");
        Logout.getStyleClass().add("category");
        MyProfile.getStyleClass().add("category");
        SellerMode.getStyleClass().add("category");
        VBox menuProfil = new VBox(40, fotoPane, MyProfile, SellerMode, Logout);
        menuProfil.setAlignment(Pos.CENTER);
        Rectangle sisiKiriProfileSceneElm = new Rectangle(201, 410);
        sisiKiriProfileSceneElm.setStyle("-fx-fill: #ffffff; -fx-border-radius: 7");
        StackPane sisiKiriProfileScene = new StackPane(sisiKiriProfileSceneElm, menuProfil);
        sisiKiriProfileScene.setAlignment(Pos.CENTER_LEFT);

        // Tambahkan tombol unggah foto
        Button uploadPhotoButton = new Button("Upload Photo");
        uploadPhotoButton.getStyleClass().add("ButtonProfil");
        menuProfil.getChildren().add(uploadPhotoButton);

        uploadPhotoButton.setOnAction(e -> {
            File file = photoUploader.upload();
            if (file != null) {
                ImageView newFotoProfil = imageSet.setImages(file, 103, 98);
                fotoPane.getChildren().set(0, newFotoProfil);
                file = file;

                try {
                    boolean isSuccesfullUpdated = UserController.updateUser(userId,
                            user.getName(), user.getUsername(),
                            user.getPassword(), user.getEmail(), user.getAlamat(), user.getPhone(), user.getGender(),
                            file);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });

        MyProfile.setStyle("-fx-background-color: #DEA0BC;");
        SellerMode.setStyle("-fx-background-color: #ffffff;");
        Logout.setStyle("-fx-background-color: #ffffff;");
        Button askForComplete = new Button();


        MyProfile.setOnMouseClicked(e -> {
            MyProfile.setStyle("-fx-background-color: #DEA0BC;");
            SellerMode.setStyle("-fx-background-color: #ffffff;");
            Logout.setStyle("-fx-background-color: #ffffff;");
        });

        SellerMode.setOnMouseClicked(e -> {
            if (user.getAlamat() != null && user.getGender() != null && user.getPhone() != null) {
                SellerMode.setStyle("-fx-background-color: #DEA0BC;");
                MyProfile.setStyle("-fx-background-color: #ffffff;");
                Logout.setStyle("-fx-background-color: #ffffff;");
                // sellerMode = true;
                try {
                    new ProfileScene(stage).showSellerMode(userId, sisiKiriProfileScene);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                MyProfile.setStyle("-fx-background-color: #DEA0BC;");
                SellerMode.setStyle("-fx-background-color: #ffffff;");
                askForComplete.setText("Complete your profile first");
                askForComplete.getStyleClass().add("ButtonProfil");
                askForComplete.setVisible(true);
            }
        });
        Button confirLogout = new Button("Klik jika ingin logout");
        Button tidakJadiLogout = new Button("Tidak jadi logout");
        
        confirLogout.getStyleClass().add("ButtonProfil");
        tidakJadiLogout.getStyleClass().add("ButtonProfil");

        // Informasi Profil
        Label infoProfilLabel = new Label("Informasi Profil");
        infoProfilLabel.setStyle(
                "-fx-font-weight: bold; -fx-font-family: 'Estedad-VF'; -fx-font-fill: #000000; -fx-font-size: 17px;");
        infoProfilLabel.getStyleClass().add("labelSubProfil");

        Label namaLabel = new Label("Nama");
        TextField namaField = new TextField(user.getName());
        namaField.setEditable(false);
        namaLabel.getStyleClass().add("labelIMyProfil");
        namaField.getStyleClass().add("fieldMyProfil");

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField(user.getEmail());
        emailField.setEditable(false);
        emailLabel.getStyleClass().add("labelIMyProfil");
        emailField.getStyleClass().add("fieldMyProfil");

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField(user.getUsername());
        usernameField.setEditable(false);
        usernameLabel.getStyleClass().add("labelIMyProfil");
        usernameField.getStyleClass().add("fieldMyProfil");

        PasswordField passwordField = new PasswordField();
        passwordField.setText("*****");
        Label passwordLabel = new Label("Password");
        passwordField.setEditable(false);
        passwordLabel.getStyleClass().add("labelIMyProfil");
        passwordField.getStyleClass().add("fieldMyProfil");

        Label genderLabel = new Label("Jenis Kelamin");
        TextField genderField = new TextField(user.getGender());
        genderField.setEditable(false);
        genderLabel.getStyleClass().add("labelIMyProfil");
        genderField.getStyleClass().add("fieldMyProfil");

        GridPane infoDataUserGrid = new GridPane();
        infoDataUserGrid.setHgap(10);
        infoDataUserGrid.setVgap(10);
        infoDataUserGrid.add(namaLabel, 0, 0);
        infoDataUserGrid.add(namaField, 1, 0);
        infoDataUserGrid.add(emailLabel, 0, 1);
        infoDataUserGrid.add(emailField, 1, 1);
        infoDataUserGrid.add(usernameLabel, 0, 2);
        infoDataUserGrid.add(usernameField, 1, 2);
        infoDataUserGrid.add(passwordLabel, 0, 3);
        infoDataUserGrid.add(passwordField, 1, 3);
        infoDataUserGrid.add(genderLabel, 0, 4);
        infoDataUserGrid.add(genderField, 1, 4);
        // infoDataUserGrid.add(btnEdit, 10, 0);
        // infoDataUserGrid.add(btnSaveChanges, 10, 0);
        infoDataUserGrid.getStyleClass().add("InformasiData");

        VBox infoBox = new VBox(10, infoProfilLabel, infoDataUserGrid);
        Rectangle persegiAbu = new Rectangle();
        persegiAbu.getStyleClass().add("persegiAbuProfile");
        StackPane inforProfil = new StackPane(persegiAbu, infoBox);

        // Detail Alamat
        Label detailAlamatLabel = new Label("Detail Alamat");
        detailAlamatLabel.setStyle(
                "-fx-font-weight: bold; -fx-font-family: 'Estedad-VF'; -fx-font-fill: #000000; -fx-font-size: 17px;");
        detailAlamatLabel.getStyleClass().add("labelSubProfil");

        Label phoneLabel = new Label("Nomor Telepon");
        TextField phoneField = new TextField(user.getPhone());
        phoneField.setEditable(false);
        phoneField.setPromptText("Nomor Telepon");
        phoneLabel.getStyleClass().add("labelIMyProfil");
        phoneField.getStyleClass().add("fieldMyProfil");

        Label addressLabel = new Label("Alamat");
        TextField addressField = new TextField(user.getAlamat());
        addressField.setEditable(false);
        addressField.setPromptText("Jalan, Kelurahan, Kecamatan, Kab/Kota, Provinsi, Kode pos");
        addressField.setPrefColumnCount(30);
        addressLabel.getStyleClass().add("labelIMyProfil");
        addressField.maxHeight(45);
        addressField.getStyleClass().add("fieldMyProfil");

        Button btnEdit = new Button("Edit Informasi Profil dan Detail Alamat");
        Button btnSaveChanges = new Button("Simpan Perubahan");
        btnEdit.getStyleClass().add("ButtonProfil");
        btnSaveChanges.getStyleClass().add("ButtonProfil");
        btnSaveChanges.setVisible(false);

        btnEdit.setOnAction(e -> {
            phoneField.setEditable(true);
            addressField.setEditable(true);
            namaField.setEditable(true);
            genderField.setEditable(true);
            passwordField.setEditable(true);
            // passwordField.setText("");
            btnEdit.setVisible(false);
            btnSaveChanges.setVisible(true);
        });

        btnSaveChanges.setOnAction(e -> {
            // Simpan perubahan ke database (implementasi tergantung pada aplikasi)
            btnEdit.setVisible(false);
            btnSaveChanges.setVisible(true);
            String alamat = addressField.getText();
            String phoneNum = phoneField.getText();
            String name = namaField.getText();
            String password = passwordField.getText();
            String passwordToUpdate = user.getPassword();
            if (!password.isEmpty()) {
                if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                    passwordField.setText(null);
                    passwordField.setPromptText("Password minimal 8 karakter terdiri\nhurufbesar, huruf kecil, dan angka");
                    return;
                }
                passwordToUpdate = PasswordHasher.doHashing(password);
            }
            String gender = genderField.getText();
            boolean isSuccesfullUpdated;
            try {
                isSuccesfullUpdated = UserController.updateUserMyProfile(userId, name,
                        user.getUsername(), passwordToUpdate,
                        user.getEmail(), alamat, phoneNum, gender);
                if (isSuccesfullUpdated) {
                    btnSaveChanges.setVisible(false);
                    btnEdit.setVisible(true);
                    
                    // ProfileScene.showMyProfile(userId, sisiKiriProfileScene);
                    return;

                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        });

        GridPane detailAlamatGrid = new GridPane();
        detailAlamatGrid.setHgap(10);
        detailAlamatGrid.setVgap(10);
        detailAlamatGrid.add(detailAlamatLabel, 0, 0);
        detailAlamatGrid.add(phoneLabel, 0, 1);
        detailAlamatGrid.add(phoneField, 1, 1);
        detailAlamatGrid.add(addressLabel, 0, 2);
        detailAlamatGrid.add(addressField, 1, 2);

        // Mengatur ukuran GridPane
        detailAlamatGrid.setMinWidth(400);
        detailAlamatGrid.setMinHeight(100);
        StackPane btnEditdanSave = new StackPane(btnSaveChanges, btnEdit);
        btnEditdanSave.setAlignment(Pos.CENTER);
        VBox addressBox = new VBox(10, detailAlamatLabel, detailAlamatGrid, btnEditdanSave);

        VBox infoAndAddressBox = new VBox(20, inforProfil, addressBox);

        ScrollPane scrollPane = new ScrollPane(infoAndAddressBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(700);

        HBox keseluruhanScene = new HBox(10, sisiKiriProfileScene, scrollPane);

        // Root layout
        VBox navbar = Navbar.getNavbar(stage, userId);
        // if (sellerMode) {
        //     navbar = Navbar.getNavbarSeller(stage, userId);
        // } else{
        //     navbar = Navbar.getNavbar(stage, userId);
        // }

        VBox mainContent = new VBox(navbar, keseluruhanScene);
        // mainContent.getStyleClass().add("mainContent");

        StackPane rootPane = new StackPane(mainContent);
        Scene scene = new Scene(rootPane, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("My Profile");
        stage.show();

        confirLogout.setVisible(false);
        tidakJadiLogout.setVisible(false);
        askForComplete.setVisible(false);
        rootPane.getChildren().add(askForComplete);

        Logout.setOnMouseClicked(e -> {
            Logout.setStyle("-fx-background-color: #DEA0BC;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            confirLogout.setVisible(true);
            tidakJadiLogout.setVisible(true);
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0); // Set the radius of the shadow
            dropShadow.setOffsetX(0); // Set the horizontal offset of the shadow
            dropShadow.setOffsetY(0); // Set the vertical offset of the shadow
            dropShadow.setColor(Color.color(0.0, 0.0, 0.0, 0.5)); // Set the color of the shadow
            dropShadow.setSpread(3); // Set the spread of the shadow

            VBox logoutConfir = new VBox(confirLogout, tidakJadiLogout);
            logoutConfir.setEffect(dropShadow);
            logoutConfir.setAlignment(Pos.CENTER);
            rootPane.getChildren().add(logoutConfir);

        });
        confirLogout.setOnMouseClicked(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();

        });
        tidakJadiLogout.setOnMouseClicked(e -> {
            tidakJadiLogout.setVisible(false);
            confirLogout.setVisible(false);
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
        });

        askForComplete.setOnAction(e->{
            askForComplete.setVisible(false);
        });
    }
}
