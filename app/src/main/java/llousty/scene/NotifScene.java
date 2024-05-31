package llousty.scene;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Notif;
import llousty.Utils.RemoveIndex;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.NotifController;

public class NotifScene {
    private Stage stage;
    
    public NotifScene(Stage stage) {
        this.stage = stage;
    }
    

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        List<Notif> notifs = NotifController.getAllNotifByUserId(userId);


        VBox listNotif = new VBox();
        ImageView notifImage = imageSet.setImages("/images/product/minus.png", 50, 50);
        for (Notif notif : notifs) {
            if (notif.getType().equals("information")) {
                
            } else if (notif.getType().equals("delivery")) {
                
            }

            
            Label date = new Label(notif.getDateSent());
            Label title = new Label(notif.getTitle());
            Label contain = new Label(notif.getText());

            VBox obj1 = new VBox(date, title, contain);
            HBox obj2 = new HBox(notifImage, obj1);

            Label coverLabel = new Label();
            coverLabel.setVisible(false);
            StackPane cover = new StackPane(obj2, coverLabel);


            Label gap = new Label();
            gap.getStyleClass().add("gap");

            int idGet = notif.getId();
            String titleGet = notif.getTitle();
            String textGet = notif.getText();
            int userIdGet = notif.getUserId();
            String dateSentGet = notif.getDateSent();
            String typeGet = notif.getType();
            boolean isSuccesfulUpdated = false;



            // listNotif.getChildren().addAll(gap, );

        }

        //MAIN 


        VBox notifRoot = new VBox(Navbar.getNavbar(stage, userId));


        Scene scene = new Scene(notifRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
    
}
