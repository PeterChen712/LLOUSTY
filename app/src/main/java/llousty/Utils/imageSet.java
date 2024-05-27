package llousty.Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class imageSet {// untuk panggil gambar agar cukup 1 obj gamabr saja dipake selama program jalan 
    public static ImageView setImages(String url){
        ImageView imageView = new ImageView(new Image(url));
        return imageView;
    }

    public static ImageView setImages(String url, double width, double height){
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitWidth(width); 
        imageView.setFitHeight(height);
        return imageView;
    }

    public static ImageView setImages(ImageView image, double width, double height){
        image.setFitWidth(width);
        image.setFitHeight(height);
        return image;
    }

    public static ImageView setImages(ImageView image){
        return image;
    }

}
