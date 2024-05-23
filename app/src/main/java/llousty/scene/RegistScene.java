package llousty.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.controller.UserController;

public class RegistScene {
    Stage stage;

    public RegistScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Label namaLabel = new Label();
        // Label userLabel = new Label("Username");

        // Label passLabel = new Label("Password")
        Label labelRegist = new Label("Register");
        labelRegist.getStyleClass().add("labelLogin");

        TextField nama = new TextField();
        nama.setPromptText("Name");
        nama.getStyleClass().add("textfield");

        TextField email = new TextField();
        email.setPromptText("Email");
        email.getStyleClass().add("textfield");

        TextField userName = new TextField();
        userName.setPromptText("Username");
        userName.getStyleClass().add("textfield");

        TextField passWord = new TextField();
        passWord.setPromptText("Password");
        passWord.getStyleClass().add("textfield");

        Text status = new Text("");

        Button btnRegist = new Button("register");
        btnRegist.setOnAction(e -> {
            String name = nama.getText();
            String username = userName.getText();
            String password = passWord.getText();
            String emailRegis = email.getText();
            // while (RegistController.UserandEmail(username, emailRegis)) {
            // status.setText("sudah digunakan");
            // }
            UserController.registAdd(name, username, password, emailRegis);
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        Text loginText = new Text("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.getStyleClass().add("HyperLink");
        loginLink.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        HBox hBox = new HBox(loginText, loginLink);
        hBox.setAlignment(Pos.CENTER);
        VBox layout = new VBox(labelRegist, status, nama, email, userName, passWord, btnRegist, hBox);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        Scene scene = new Scene(layout, 750, 500);
        scene.getStylesheets().add("styles.css");

        stage.setScene(scene);
        stage.setTitle("Regist");
        stage.show();
    }
}
