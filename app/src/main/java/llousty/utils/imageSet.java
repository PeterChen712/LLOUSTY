package llousty.Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class imageSet {
    public static ImageView setImages(String url, double width, double height){
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitWidth(width); 
        imageView.setFitHeight(height);
        return imageView;
    }
}
