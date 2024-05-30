package llousty.scene;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.components.Navbar;

public class LoadingScene {
    private Stage stage;

    public LoadingScene(Stage stage) {
        this.stage = stage;
    }

    public void Loading() throws InterruptedException{
        StackPane loadingRoot = new StackPane();
        Thread.sleep(1000);

        Scene scene = new Scene(loadingRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
