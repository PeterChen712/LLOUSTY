package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
        VBox chatLayout = new VBox();
        for (int chatId : listChatId) {
            User chating = UserController.getUserById(chatId);
            ImageView chatterImage = imageSet.setImages(chating.getPhotoFile(), 40, 40);
            ImageView border = imageSet.setImages("/images/navbar/border.png", 40, 40);
            StackPane imageCombine = new StackPane(chatterImage, border);
            imageCombine.setAlignment(Pos.CENTER_LEFT);
            Button chatMenu = new Button();
            chatMenu.getStyleClass().add("chatprofile");
            chatMenu.setGraphic(imageCombine);

            chatMenu.setOnAction(e->{
                try {
                    new ChatScene(stage).show(chating.getId(), userId);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            Label profileName = new Label(chating.getName());
            profileName.getStyleClass().add("productNameCart");
            Label profileUser = new Label(chating.getUsername());
            profileUser.getStyleClass().add("sellerName");
            Label gap = new Label();
            gap.getStyleClass().add("gap2");

            


            VBox profileInfo = new VBox(profileName, profileUser);
            profileInfo.setSpacing(10);

            HBox profileBar = new HBox(10, chatMenu, profileInfo);
            VBox profileBarVbox = new VBox(profileBar);
            profileBarVbox.setAlignment(Pos.CENTER);
            profileBarVbox.setPadding(new Insets(0, 0, 0, 0));
            Label back = new Label();
            back.getStyleClass().add("backChat");
            back.setAlignment(Pos.CENTER);
            StackPane stackPane = new StackPane(back, profileBarVbox);
            chatLayout.getChildren().addAll(gap, stackPane);

        
        }
        

        VBox chatMenuRoot = new VBox(Navbar.getNavbar(stage, userId), chatLayout);

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
