package llousty.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Utils.imageSet;
import llousty.controller.UserController;

public class RegistScene implements ShowScene{
    Stage stage;

    public RegistScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Label namaLabel = new Label();
        // Label userLabel = new Label("Username");

        // Label passLabel = new Label("Password")
        Label labelRegist = new Label("Register");
        labelRegist.getStyleClass().add("labelLoginAndRegist");

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

        Text status = new Text("\n\n");
        status.getStyleClass().add("textStatus");

        Button btnRegist = new Button("register");
        btnRegist.getStyleClass().add("buttonLoginandRegist");
        btnRegist.setOnAction(e -> {
            String name = nama.getText();
            String username = userName.getText();
            String password = passWord.getText();
            String emailRegis = email.getText();
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || emailRegis.isEmpty()) {
                status.setText("\n\t  Data cannot be empty\n");
                return;
            }
            if (!emailRegis.matches("^[\\w._%+-]+@(gmail\\.com|yahoo\\.com)$")) {
                status.setText("\t  Example correct email :\t\n   @gmail.com or @yahoo.com\n");
                return;
            }

            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                status.setText("\tPassword at least have 8 \n\t    character containt\n  Uppercase, Lowercase, and digit");
                return;
            }

            if (UserController.isUsernameOrEmailTaken(username, emailRegis)) {
                status.setText("\t     Email or username \n\t         already taken\n");
                return;
            }
            UserController.registAdd(name, username, password, emailRegis);
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        Text loginText = new Text("Already have an account?");
        loginText.getStyleClass().add("textHyper");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.getStyleClass().add("HyperLink");
        loginLink.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        Rectangle elmPersegi = new Rectangle(374, 500);
        elmPersegi.getStyleClass().addAll("elementPersegi");

        ImageView logo = imageSet.setImages("/images/logo/logo3.png", 400, 400);

        HBox hBox = new HBox(loginText, loginLink);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox(labelRegist, nama, email, userName, passWord, btnRegist, hBox);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(15);
        VBox vBox2 = new VBox(status,vBox1);
        HBox hBox2 = new HBox(vBox2);

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
        stage.show();
    }
}
