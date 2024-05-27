package llousty.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.stage.FileChooser;

public class photoUploader {

    public static File upload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            return selectedFile;
        }
        return null;
    }
}