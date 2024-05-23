package llousty.scene;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScene {
    Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox vBox = new VBox();

        Scene scene = new Scene(vBox, 750, 500);
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

}
