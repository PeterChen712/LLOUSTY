package llousty.scene;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.User;
import llousty.Utils.imageSet;
import llousty.config.DbConfig;
import llousty.controller.UserController;
import llousty.controller.UserController;

public class LoginScene {
    Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // ImageView profileImage = new ImageView();
        // profileImage.setFitHeight(200);
        // profileImage.setFitWidth(200);
        // profileImage.getStyleClass().add("imageView");
        // // ImageView profileImage = new ImageView();
        // // profileImage.setImage(new Image("/images/logo2.png"));

        // Text text = new Text("Fill your beauty with LLOUSTY");
        // text.getStyleClass().add("text");

        Label labelLogin = new Label("Login");
        labelLogin.getStyleClass().add("labelLoginAndRegist");

        Label status = new Label();
        status.getStyleClass().add("textStatus");

        TextField inUser = new TextField();
        inUser.setPromptText("Username");
        inUser.getStyleClass().add("textfield");

        PasswordField inPass = new PasswordField();
        inPass.setPromptText("Password");
        inPass.getStyleClass().add("textfield");

        Button btnLogin = new Button("login");
        btnLogin.getStyleClass().add("buttonLoginandRegist");
        btnLogin.setOnAction(e -> {
            String username = inUser.getText();
            String password = inPass.getText();
            if (username.isEmpty() || password.isEmpty()) {
                status.setText("username dan password harus diisi");
                return;
            }

            User user = new UserController().validasiLogin(username, password);
            if (user != null) {
                int id = user.getId();
                status.setText("Berhasil");
                HomeScene homeScene = new HomeScene(stage);
                try {
                    homeScene.show(id);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                status.setText("username atau password salah");
            }
        });

        Text daftar = new Text("Don't have an account?");
        daftar.getStyleClass().add("textHyper");
        Hyperlink regis = new Hyperlink("Register");
        regis.getStyleClass().add("HyperLink");
        regis.setOnAction(e -> {
            RegistScene registScene = new RegistScene(stage);
            registScene.show();
        });

        // Text mottoText = new Text("\t WELCOME\nFill Your Marvellous Beauty \n\twith
        // LLOUSTY");
        // mottoText.getStyleClass().add("mottoText");

        Rectangle elmPersegi = new Rectangle(374, 500);
        elmPersegi.getStyleClass().addAll("elementPersegi");

        ImageView logo = imageSet.setImages("/images/logo/logo3.png", 400, 400);

        HBox hBox = new HBox(daftar, regis);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(labelLogin, status, inUser, inPass, btnLogin, hBox);
        HBox hBox2 = new HBox(vBox);
        // hBox2.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(elmPersegi, logo, hBox2);

        // Mengatur posisi elemen di StackPane
        stackPane.setAlignment(elmPersegi, Pos.CENTER_RIGHT);
        stackPane.setAlignment(logo, Pos.CENTER_RIGHT);
        stackPane.setAlignment(hBox2, Pos.CENTER_LEFT);

        // Menambahkan margin pada hBox2 untuk memposisikannya lebih baik
        StackPane.setMargin(hBox2, new Insets(0, 0, 0, 70)); // Mengatur margin kir
        Scene scene = new Scene(stackPane, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
