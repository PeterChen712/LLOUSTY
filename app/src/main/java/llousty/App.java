package llousty;

import javafx.application.Application;
import javafx.stage.Stage;
import llousty.config.DbConfig;
import llousty.scene.LoginScene;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginScene firstScene = new LoginScene(primaryStage);
        firstScene.show();
        primaryStage.setResizable(false);
        DbConfig.getConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
