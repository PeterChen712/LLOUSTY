package llousty;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    //buat ukuran lebar dan tinggi awal stage waktu di run
    final private static int width = 640;
    final private static int height = 480;
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //membuat scane 
        StackPane mainRoot = new StackPane();
        Scene scene = new Scene(mainRoot, getWidth(), getHeight());
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());


        primaryStage.setTitle("LLOUSTY");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
