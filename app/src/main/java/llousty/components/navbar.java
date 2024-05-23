package llousty.components;

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
import llousty.Utils.imageSet;

public class navbar {
    private static String searchText = "Search";
    public static void setSearchText(String searchText) {
        navbar.searchText = searchText;
    }
    public static String getSearchText() {
        return searchText;
    }

    public static HBox setNavbar(){
        //gambar untuk sementara search dulu
        
        //Buat tombol home
        // Home Button
        ImageView homeLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Label homeLabel = new Label("Home");
        VBox homeContainer = new VBox(homeLogo, homeLabel);
        homeContainer.setAlignment(Pos.CENTER);
        Label homeButton = new Label();
        homeButton.setGraphic(homeContainer);
        homeButton.getStyleClass().add("navbar");
        homeButton.setOnMouseClicked(event -> {
            System.out.println("Home Label diklik!");
        });

        // Cart Button
        ImageView cartLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Label cartLabel = new Label("Cart");
        VBox cartContainer = new VBox(cartLogo, cartLabel);
        cartContainer.setAlignment(Pos.CENTER);
        Label cartButton = new Label();
        cartButton.setGraphic(cartContainer);
        cartButton.getStyleClass().add("navbar");
        cartButton.setOnMouseClicked(event -> {
            System.out.println("Cart Label diklik!");
        });

        // Wishlist Button
        ImageView wishlistLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Label wishlistLabel = new Label("Wishlist");
        VBox wishlistContainer = new VBox(wishlistLogo, wishlistLabel);
        wishlistContainer.setAlignment(Pos.CENTER);
        Label wishlistButton = new Label();
        wishlistButton.setGraphic(wishlistContainer);
        wishlistButton.getStyleClass().add("navbar");
        wishlistButton.setOnMouseClicked(event -> {
            System.out.println("Wishlist Label diklik!");
        });

        // Orders Button
        ImageView ordersLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Label ordersLabel = new Label("Orders");
        VBox ordersContainer = new VBox(ordersLogo, ordersLabel);
        ordersContainer.setAlignment(Pos.CENTER);
        Label ordersButton = new Label();
        ordersButton.setGraphic(ordersContainer);
        ordersButton.getStyleClass().add("navbar");
        ordersButton.setOnMouseClicked(event -> {
            System.out.println("Orders Label diklik!");
        });

        // Profile Button
        ImageView profileLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Label profileLabel = new Label("Profile");
        VBox profileContainer = new VBox(profileLogo, profileLabel);
        profileContainer.setAlignment(Pos.CENTER);
        Label profileButton = new Label();
        profileButton.setGraphic(profileContainer);
        profileButton.getStyleClass().add("navbar");
        profileButton.setOnMouseClicked(event -> {
            System.out.println("Profile Label diklik!");
        });

        //tombol cari
        ToolBar searchToolbar = new ToolBar();
        TextField searchBar = new TextField();
        searchBar.setPromptText(getSearchText());
        searchBar.getStyleClass().add("searchBar");
        ImageView searchLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Button searchButton = new Button();
        searchButton.setGraphic(searchLogo);
        searchButton.getStyleClass().add("searchButton");    
        searchToolbar.getItems().addAll(new ToolBar(searchBar), new ToolBar(searchButton));

        searchButton.setOnAction(e->{
            setSearchText(searchBar.getText());
            // ke scene lain
        });
        
        searchBar.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                searchButton.fire();
            }
        });



        HBox navbar = new HBox(homeButton, cartButton, wishlistButton, ordersButton, searchBar, searchButton, profileButton);
        navbar.setSpacing(0);
        navbar.setAlignment(Pos.TOP_CENTER);
        navbar.setStyle("-fx-background-color: #333333;"); // Warna abu-abu gelap
        return navbar;







    }
}
