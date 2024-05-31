package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.components.Navbar;

public class ChatMenuScene {
    private Stage stage;

    public ChatMenuScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        VBox chatMenufRoot = new VBox(Navbar.getNavbar(stage, userId));

        Scene scene = new Scene(chatMenufRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
