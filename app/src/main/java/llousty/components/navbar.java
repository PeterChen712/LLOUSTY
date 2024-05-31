package llousty.components;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.Models.Notif;
import llousty.Models.User;
import llousty.Utils.AudioFile;
import llousty.Utils.imageSet;
import llousty.controller.NotifController;
import llousty.controller.UserController;
import llousty.scene.CartScene;
import llousty.scene.HomeScene;
import llousty.scene.NotifScene;
import llousty.scene.SearchScene;
import llousty.scene.TransactionScene;
import llousty.scene.ProfileScene;

public class Navbar {
    private static String searchText = "Search";

    public static void setSearchText(String searchText) {
        Navbar.searchText = searchText;
    }

    public static String getSearchText() {
        return searchText;
    }

    public static VBox getNavbar(Stage stage, int id) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        User user = UserController.getUserById(id);

        ImageView brand = imageSet.setImages("/images/logo/logo3.png", 20, 20);
        Text textBrand = new Text("FULFILL YOUR MARVELLOUS BEAUTY WITH LLOUSTY");
        textBrand.getStyleClass().add("textBrand");
        HBox brandItem = new HBox(brand, textBrand);
        brandItem.setAlignment(Pos.CENTER);
        brandItem.setSpacing(10);
        Label backBrand = new Label();
        backBrand.getStyleClass().add("backBrand");
        StackPane brandLayout = new StackPane(backBrand, brandItem);
        brandLayout.setAlignment(Pos.CENTER);

        Label navbarLabel = new Label();
        navbarLabel.getStyleClass().add("backNavbar");
        if (user.getSellerMode().equals("Seller")) {
            navbarLabel.setStyle("-fx-background-color:#000000;");
        } 
        
        Label homeMenu = new Label("HOME");
        homeMenu.setStyle("-fx-font-size: 12px;");
        if (user.getSellerMode().equals("Seller")) {
            homeMenu.getStyleClass().add("navbarSeller");
        } else{
            homeMenu.getStyleClass().add("navbar"); 
        }
        homeMenu.setAlignment(Pos.CENTER);
        homeMenu.setOnMouseClicked(event -> {
            try {
                new HomeScene(stage).show(id);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Wishlist Button
        Label wishlistMenu = new Label("CHAT");
        wishlistMenu.setStyle("-fx-font-size: 12px;");
        if (user.getSellerMode().equals("Seller")) {
            wishlistMenu.getStyleClass().add("navbarSeller");
        } else{
            wishlistMenu.getStyleClass().add("navbar");
        }
        wishlistMenu.setAlignment(Pos.CENTER);
        wishlistMenu.setOnMouseClicked(event -> {
            System.out.println("wishlist Label diklik!");
        });

        // Orders Button
        Label ordersMenu = new Label("MY ORDER");
        ordersMenu.setStyle("-fx-font-size: 12px;");
        if (user.getSellerMode().equals("Seller")) {
            ordersMenu.getStyleClass().add("navbarSeller");
        } else{
            ordersMenu.getStyleClass().add("navbar");
        }
        ordersMenu.setAlignment(Pos.CENTER);
        ordersMenu.setOnMouseClicked(event -> {
            try {
                new TransactionScene(stage).show(id);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // tombol cari
        // ToolBar searchToolbar = new ToolBar();
        TextField searchBar = new TextField();
        searchBar.setPromptText(getSearchText());
        searchBar.getStyleClass().add("searchBar");
        ImageView searchLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Button searchButton = new Button();
        searchButton.setGraphic(searchLogo);
        searchButton.getStyleClass().add("searchButton");
        searchButton.setOnAction(e -> {
            setSearchText(searchBar.getText());
            try {
                new SearchScene(stage).show(id);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        searchBar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                searchButton.fire();
            }
        });

        //NOTIF
        ImageView notifLogo;
        List<Notif> notifs = NotifController.getAllNotifByUserId(id);
        if (user.getSellerMode().equals("Seller")) {
            if (user.getTotalNotif() == notifs.size()) {
                notifLogo = imageSet.setImages("/images/navbar/notifSeller.png", 40, 40);
            }else{
                notifLogo = imageSet.setImages("/images/navbar/notifPingSeller.png", 40, 40);
                AudioFile.Audio("/audio/notifAudio.wav");
            }
        } else{
            if (user.getTotalNotif() == notifs.size()) {
                notifLogo = imageSet.setImages("/images/navbar/notif.png", 40, 40);
            }else{
                notifLogo = imageSet.setImages("/images/navbar/notifPing.png", 40, 40);
                AudioFile.Audio("/audio/notifAudio.wav");
            }
        }
        
        Label notifMenu = new Label();
        notifMenu.setStyle(" -fx-pref-width: 40px;");
        if (user.getTotalNotif() == notifs.size()) {
            notifMenu.getStyleClass().add("navbarIconSeller");
        }else{
            notifMenu.getStyleClass().add("navbarIcon");
        }
        notifMenu.setGraphic(notifLogo);
        notifMenu.setOnMouseClicked(e -> {
            try {
                new NotifScene(stage).show(id);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        notifMenu.setPadding(new Insets(0, 10, 0, 10));

        // Profile
        ImageView profileLogo = imageSet.setImages(user.getPhotoFile(), 40, 40);
        ImageView border;
        if (user.getTotalNotif() == notifs.size()) {
            border = imageSet.setImages("/images/navbar/borderSeller.png", 40, 40);
        }else{
            border = imageSet.setImages("/images/navbar/border.png", 40, 40);
        }
        StackPane imageCombine = new StackPane(profileLogo, border);
        imageCombine.setAlignment(Pos.CENTER_LEFT);
        Button profileMenu = new Button();
        if (user.getSellerMode().equals("Seller")) {
            profileMenu.getStyleClass().add("navbarIconSeller");
        } else{
            profileMenu.getStyleClass().add("navbarIcon");
        }
        profileMenu.setGraphic(imageCombine);

        profileMenu.setOnAction(e -> {
            ProfileScene profileScene = new ProfileScene(stage);
            try {
                StackPane sisiKananPane = new StackPane();
                profileScene.show(id, id);
                // profileScene.showMyProfile(id, sisiKananPane);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        // Cart Button
        ImageView cartLogo;
        if (user.getSellerMode().equals("Seller")) {
            cartLogo = imageSet.setImages("/images/navbar/cartSeller.png", 60, 40);
        } else{
            cartLogo = imageSet.setImages("/images/navbar/cart.png", 60, 40);
        }
        Label cartMenu = new Label();
        cartMenu.getStyleClass().add("navbarIcon");
        cartMenu.setGraphic(cartLogo);
        cartMenu.setOnMouseClicked(e -> {
            try {
                new CartScene(stage).show(id);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        HBox menu = new HBox(homeMenu, wishlistMenu, ordersMenu, searchBar, searchButton, notifMenu, profileMenu, cartMenu);
        menu.setAlignment(Pos.TOP_CENTER);
        // menu.setPadding(new Insets(5, 0, 0, 0));
        StackPane menuBar = new StackPane(navbarLabel, menu);

        VBox navbar = new VBox(brandLayout, menuBar);
        return navbar;

    }

}
