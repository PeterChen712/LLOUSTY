package llousty.Utils;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class imageSet {// untuk panggil gambar agar cukup 1 obj gamabr saja dipake selama program jalan
    public static ImageView setImages(String url) {
        ImageView imageView = new ImageView(new Image(url));
        return imageView;
    }

    public static ImageView setImages(String url, double width, double height) {
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public static ImageView setImages(ImageView image, double width, double height) {
        image.setFitWidth(width);
        image.setFitHeight(height);
        return image;
    }

    public static ImageView setImages(ImageView image) {
        return image;
    }

    public static ImageView setImages(File selectedFile, int width, int height) {
        ImageView imageView = new ImageView();
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;

    }

}
