package llousty;

import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import llousty.config.DbConfig;
import llousty.scene.ChatScene;
import llousty.scene.HomeScene;
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

    private final Image icon = new Image(getClass().getResourceAsStream("/images/logo/logo2.png"));

    @Override
    public void start(Stage primaryStage) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        LoginScene firstScene = new LoginScene(primaryStage);
        // firstScene.show();
        HomeScene homeScene = new HomeScene(primaryStage);
        // homeScene.show(11);

        ChatScene chatScene = new ChatScene(primaryStage);
        chatScene.show(11, 12);

        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("LLOUSTY");
        DbConfig.getConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
