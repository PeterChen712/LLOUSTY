package llousty;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import llousty.config.DbConfig;
import llousty.scene.LoginScene;

public class App extends Application {
    final private static int width = 750;
    final private static int height = 480;
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    private final Image icon = new Image(getClass().getResourceAsStream("/images/logo2.png"));


    @Override
    public void start(Stage primaryStage) {
        LoginScene firstScene = new LoginScene(primaryStage);
        firstScene.show();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("LLOUSTY");
        DbConfig.getConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
