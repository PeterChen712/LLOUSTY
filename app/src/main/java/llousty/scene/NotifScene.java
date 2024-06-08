package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Notif;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.NotifController;
import llousty.controller.UserController;

public class NotifScene implements ShowScene{
    private Stage stage;
    
    public NotifScene(Stage stage) {
        this.stage = stage;
    }
    

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        List<Notif> notifs = NotifController.getAllNotifByUserId(userId);
        boolean isSuccesfullUpdated;
        try {
            isSuccesfullUpdated = UserController.updateTotalNotif(userId, notifs.size());
            System.out.println(isSuccesfullUpdated);
        } catch (Exception e) {
            e.printStackTrace();
        }




        VBox listNotif = new VBox();
        ListIterator<Notif> iterator = notifs.listIterator(notifs.size());
        while (iterator.hasPrevious()) {
            Notif notif = iterator.previous();
            ImageView image = new ImageView();
            if (notif.getType().equals("information")) {
                image = imageSet.setImages("/images/notif/info.png", 100, 100);
            } else if (notif.getType().equals("accept")) {
                image = imageSet.setImages("/images/notif/accept.png", 100, 100);
            } else if (notif.getType().equals("cancel")) {
                image = imageSet.setImages("/images/notif/cancel.png", 100, 100);
            }


            Label title = new Label(notif.getTitle());
            title.getStyleClass().add("notifTitle");

            Label date = new Label(notif.getDateSent());
            date.getStyleClass().add("notifDate");

            Label contain = new Label(notif.getText());
            contain.getStyleClass().add("notifText");

            VBox textBox = new VBox(5, title, date, contain);
            textBox.getStyleClass().add("notifTextBox");

            StackPane imageWrapper = new StackPane(image);
            imageWrapper.getStyleClass().add("notifImageWrapper");

            
            
            HBox notifItem = new HBox(10, imageWrapper, textBox);
            notifItem.getStyleClass().add("notifItem");



            // Tambahkan tombol hapus notifikasi
            Button deleteButton = new Button();
            deleteButton.getStyleClass().add("delete-button");
            deleteButton.setGraphic(imageSet.setImages("/images/notif/remove.png", 20, 20));
            
            HBox deleteButtonBox = new HBox(deleteButton);
            deleteButtonBox.setMargin(deleteButton, new Insets(40, 0, 0, 200));
            notifItem.getChildren().add(deleteButtonBox);

            StackPane notifScroll = new StackPane(notifItem);
            notifScroll.getStyleClass().add("notifWrapper");

            listNotif.getChildren().add(notifScroll);


            deleteButton.setOnAction(event -> {
                // Buat transisi untuk efek fade out dan translasi ke kanan
                FadeTransition fadeOut = new FadeTransition(Duration.millis(200), notifScroll);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
    
                TranslateTransition translateOut = new TranslateTransition(Duration.millis(200), notifScroll);
                translateOut.setFromX(0);
                translateOut.setToX(notifScroll.getScene().getWidth());
    
                ParallelTransition transition = new ParallelTransition(fadeOut, translateOut);
    
                // Hapus notifikasi setelah transisi selesai
                transition.setOnFinished(e -> {
                    NotifController.deleteNotif(notif.getId());
                    listNotif.getChildren().remove(notifScroll);
                });
    
                transition.play(); // Jalankan transisi
                });
                
        }

        listNotif.getStyleClass().add("listNotif");
        listNotif.setPadding(new Insets(20));
        ScrollPane scrollPane = new ScrollPane(listNotif);
        scrollPane.setFitToWidth(true);

        //MAIN 


        VBox notifRoot = new VBox(Navbar.getNavbar(stage, userId), scrollPane);


        Scene scene = new Scene(notifRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void show() throws InterruptedException {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
    
}
