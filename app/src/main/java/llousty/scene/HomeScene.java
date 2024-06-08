package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Notif;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.AdsMaker;
import llousty.Utils.AudioFile;
import llousty.Utils.imageSet;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.NotifController;
import llousty.controller.ProductController;
import llousty.controller.UserController;

public class HomeScene implements ShowScene {
    protected Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    private static GridPane showCategory(Stage stage, String filter, int id) throws SQLException {
        List<Product> listProducts = ProductController.getAllProduct();

        GridPane productLayout = new GridPane();
        productLayout.setHgap(24);
        productLayout.setVgap(10);
        productLayout.setPadding(new Insets(0, 0, 0, 24));

        int column = 0;
        int row = 0;

        for (Product product : listProducts) {
            if (product.getCategory().equals(filter)) {

                ImageView productImage = imageSet.setImages(product.getProductPhoto(), 150, 150);
                productImage.setPreserveRatio(true);

                Label productBackground = new Label();
                productBackground.getStyleClass().add("productBackground");

                StackPane productView = new StackPane(productBackground, productImage);

                Label productName = new Label(product.getName());
                productName.getStyleClass().add("productName");
                Label productPrice = new Label("Rp" + priceFormatter.formatCurrency(product.getPrice()));
                productPrice.getStyleClass().add("productPrice");

                VBox productInfo = new VBox(productView, productName, productPrice);
                productInfo.setSpacing(10);

                VBox productBox = new VBox(productImage, productInfo);
                productBox.getStyleClass().add("productBox");
                productBox.setOnMouseClicked(e -> {
                    try {
                        new ProductScene(stage).show(id, product.getId());
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                            | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("wowo");
                });

                productLayout.add(productBox, column, row);

                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }
            }
        }
        return productLayout;
    }

    private void showMakeUp(int id, HBox category) throws SQLException, UnsupportedAudioFileException, IOException,
            LineUnavailableException, InterruptedException {
        VBox mainContent = new VBox(category, showCategory(stage, "Make Up", id));
        mainContent.setPadding(new Insets(20, 0, 0, 0));
        mainContent.setSpacing(20);
        mainContent.setPrefWidth(750);
        mainContent.getStyleClass().add("mainContent");
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);

        ImageView[] ads = {
                imageSet.setImages("/images/home/m4.png", 750, 150),
                imageSet.setImages("/images/home/m1.png", 750, 150),
                imageSet.setImages("/images/home/m3.png", 750, 150),
        };

        VBox homeRoot = new VBox(Navbar.getNavbar(stage, id), AdsMaker.showAdsAnimation(stage, ads), scrollPane);

        Scene scene = new Scene(homeRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    private void showSkinCare(int id, HBox category) throws SQLException, UnsupportedAudioFileException, IOException,
            LineUnavailableException, InterruptedException {
        VBox mainContent = new VBox(category, showCategory(stage, "Skin Care", id));
        mainContent.setPadding(new Insets(20, 0, 0, 0));
        mainContent.setSpacing(20);
        mainContent.setPrefWidth(750);
        mainContent.getStyleClass().add("mainContent");
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);

        ImageView[] ads = {
                imageSet.setImages("/images/home/s1.png", 750, 150),
                imageSet.setImages("/images/home/s2.png", 750, 150),
                imageSet.setImages("/images/home/s3.png", 750, 150),
        };

        VBox homeRoot = new VBox(Navbar.getNavbar(stage, id), AdsMaker.showAdsAnimation(stage, ads), scrollPane);

        Scene scene = new Scene(homeRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    private void showFragrance(int id, HBox category) throws SQLException, UnsupportedAudioFileException, IOException,
            LineUnavailableException, InterruptedException {
        VBox mainContent = new VBox(category, showCategory(stage, "Fragrance", id));
        mainContent.setPadding(new Insets(20, 0, 0, 0));
        mainContent.setSpacing(20);
        mainContent.setPrefWidth(750);
        mainContent.getStyleClass().add("mainContent");
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);

        ImageView[] ads = {
                imageSet.setImages("/images/home/f1.png", 750, 150),
                imageSet.setImages("/images/home/f2.png", 750, 150),
                imageSet.setImages("/images/home/f3.png", 750, 150),
        };

        VBox homeRoot = new VBox(Navbar.getNavbar(stage, id), AdsMaker.showAdsAnimation(stage, ads), scrollPane);

        Scene scene = new Scene(homeRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    public void show(int id) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException,
            InterruptedException {
        User user = UserController.getUserById(id);
        List<Notif> notifs = NotifController.getAllNotifByUserId(id);

        if (user.getTotalNotif() != notifs.size()) {
            if (App.isNotifAudio()) {
                // AudioFile.Audio(
                //         "D:\\Rudy\\file rudy\\UNHAS\\GitHub\\LLOUSTY\\app\\src\\main\\resources\\audio\\notifAudio.wav");
                App.setNotifAudio(false);
            }
        }

        // MENU
        Label makeUp = new Label("MAKE UP");
        makeUp.getStyleClass().add("category");
        Label skinCare = new Label("SKIN CARE");
        skinCare.getStyleClass().add("category");
        Label fragrance = new Label("FRAGRANCE");
        fragrance.getStyleClass().add("category");
        HBox category = new HBox(makeUp, skinCare, fragrance);
        category.setAlignment(Pos.CENTER);
        category.setSpacing(100);

        // CATEGORY
        VBox mainContent = new VBox(category, showCategory(stage, "Make Up", id));
        makeUp.setStyle("-fx-background-color: #DEA0BC;");
        mainContent.setPadding(new Insets(20, 0, 0, 0));
        mainContent.setSpacing(20);
        mainContent.setPrefWidth(750);
        mainContent.getStyleClass().add("mainContent");
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);

        ImageView[] ads = {
                imageSet.setImages("/images/home/m4.png", 750, 150),
                imageSet.setImages("/images/home/m1.png", 750, 150),
                imageSet.setImages("/images/home/m3.png", 750, 150),
        };

        VBox homeRoot = new VBox(Navbar.getNavbar(stage, id), AdsMaker.showAdsAnimation(stage, ads), scrollPane);

        Scene scene = new Scene(homeRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();

        makeUp.setOnMouseClicked(e -> {
            makeUp.setStyle("-fx-background-color: #DEA0BC;");
            skinCare.setStyle("-fx-background-color: #ffffff;");
            fragrance.setStyle("-fx-background-color: #ffffff;");
            try {
                new HomeScene(stage).showMakeUp(id, category);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        skinCare.setOnMouseClicked(e -> {
            skinCare.setStyle("-fx-background-color: #DEA0BC;");
            makeUp.setStyle("-fx-background-color: #ffffff;");
            fragrance.setStyle("-fx-background-color: #ffffff;");
            try {
                new HomeScene(stage).showSkinCare(id, category);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        fragrance.setOnMouseClicked(e -> {
            fragrance.setStyle("-fx-background-color: #DEA0BC;");
            makeUp.setStyle("-fx-background-color: #ffffff;");
            skinCare.setStyle("-fx-background-color: #ffffff;");
            try {
                new HomeScene(stage).showFragrance(id, category);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

}
