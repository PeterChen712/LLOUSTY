package llousty.scene;

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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.config.DbConfig;
import llousty.controller.UserController;
import llousty.controller.UserController;

public class LoginScene {
    Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        ImageView profileImage = new ImageView();
        profileImage.setFitHeight(200);
        profileImage.setFitWidth(200);
        profileImage.getStyleClass().add("imageView");
        // ImageView profileImage = new ImageView();
        // profileImage.setImage(new Image("/images/logo2.png"));

        Text text = new Text("Fill your beauty with LLOUSTY");
        text.getStyleClass().add("text");

        Label labelLogin = new Label("Login");
        labelLogin.getStyleClass().add("labelLogin");

        Label status = new Label("Belum Login");

        TextField inUser = new TextField();
        inUser.setPromptText("Username");
        inUser.getStyleClass().add("textfield");

        TextField inPass = new TextField();
        inPass.setPromptText("Password");
        inPass.getStyleClass().add("textfield");

        Button btnLogin = new Button("login");
        btnLogin.setOnAction(e -> {
            String username = inUser.getText();
            String password = inPass.getText();
            if (UserController.validasiLogin(username, password)) {
                status.setText("BERHASIL LOGIN");
                HomeScene homeScene = new HomeScene(stage);
                homeScene.show();
            } else {
                status.setText("GAGAL LOGIN");
            }
        });

        Text daftar = new Text("Don't have an account?");
        Hyperlink regis = new Hyperlink("Register");
        regis.getStyleClass().add("HyperLink");
        regis.setOnAction(e -> {
            RegistScene registScene = new RegistScene(stage);
            registScene.show();
        });

        HBox hBox = new HBox(daftar, regis);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(labelLogin, status, inUser, inPass, btnLogin, hBox);
        VBox vb2 = new VBox(profileImage, text);
        vb2.setSpacing(50);
        vb2.setAlignment(Pos.CENTER);
        HBox layout = new HBox(vBox, vb2);
        layout.setSpacing(50);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 750, 500);
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
