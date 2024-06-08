package llousty.scene;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import llousty.App;
import llousty.Abstract.ShowScene;

public class LoadingScene implements ShowScene {
    private Stage stage;

    public LoadingScene(Stage stage) {
        this.stage = stage;
    }

    public void show() throws InterruptedException {
        
        Image image = new Image(getClass().getResourceAsStream("/images/logo/logo2.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        Label labelLoading = new Label("Loading...");
        labelLoading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        labelLoading.setTextFill(Color.WHITE);

        VBox vBoxLoading = new VBox(labelLoading);
        vBoxLoading.setAlignment(Pos.BOTTOM_CENTER);
        vBoxLoading.setPadding(new Insets(0, 0, 20, 0));

        StackPane stackPane = new StackPane(imageView, vBoxLoading);

        stackPane.setStyle("-fx-background-color: #FF69B4;"); 

        Scene scene = new Scene(stackPane, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");

        stage.setScene(scene);
        stage.show();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), stackPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        new Thread(() -> {
            try {
                Thread.sleep(3000); 
                Platform.runLater(() -> {
                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), stackPane);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(e -> {
                        stage.close();
                        showMainScene();
                    });
                    fadeOut.play();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void showMainScene() {
        try {
            // Instantiate and show main scene
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}