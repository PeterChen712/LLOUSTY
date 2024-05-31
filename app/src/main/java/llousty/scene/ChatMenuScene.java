package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.User;
import llousty.Utils.StringListConverter;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.UserController;

public class ChatMenuScene implements ShowScene{
    private Stage stage;

    public ChatMenuScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        User user = UserController.getUserById(userId);
        List<Integer> listChatId = StringListConverter.stringToListInt(user.getListChatId());
        for (int chatId : listChatId) {
            User chating = UserController.getUserById(userId);
            // ImageView notifImage = imageSet.setImages(null, chatId, userId);
        }
        

        VBox chatMenuRoot = new VBox(Navbar.getNavbar(stage, userId));

        Scene scene = new Scene(chatMenuRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
