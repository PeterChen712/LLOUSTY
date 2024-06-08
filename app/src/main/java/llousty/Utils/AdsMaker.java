package llousty.Utils;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdsMaker {
    private static final int DELAY = 3000; // Waktu delay dalam milidetik (3 detik)
    private static final double TRANSITION_DURATION = 1.0; // Durasi transisi dalam detik

    public static HBox showAdsAnimation(Stage stage, ImageView[] ads) {
        HBox adContainer = new HBox();
        adContainer.setAlignment(Pos.CENTER);

        ImageView adView = new ImageView();
        adContainer.getChildren().add(adView);

        // Tampilkan gambar iklan pertama tanpa jeda
        adView.setImage(ads[0].getImage());

        Thread adThread = new Thread(() -> {
            int index = 0;
            while (true) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                index = (index + 1) % ads.length;
                adView.setImage(ads[index].getImage());

                TranslateTransition transition = new TranslateTransition(Duration.seconds(TRANSITION_DURATION), adView);
                transition.setFromX(stage.getWidth());
                transition.setToX(0);
                transition.play();
            }
        });
        adThread.setDaemon(true);
        adThread.start();

        return adContainer;
    }
}