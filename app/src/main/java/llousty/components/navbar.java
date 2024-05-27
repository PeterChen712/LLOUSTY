package llousty.components;

import java.sql.SQLException;

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
import llousty.Models.User;
import llousty.Utils.imageSet;
import llousty.controller.UserController;
import llousty.scene.CartScene;
import llousty.scene.HomeScene;

public class Navbar {
    private static String searchText = "Search";
    public static void setSearchText(String searchText) {
        Navbar.searchText = searchText;
    }
    public static String getSearchText() {
        return searchText;
    }

    public VBox getNavbar(Stage stage, int id) throws SQLException{
        User user = UserController.getUserById(id);
        //gambar untuk sementara search dulu
        
        
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
        Label homeMenu = new Label("HOME");
        homeMenu.getStyleClass().add("navbar");
        homeMenu.setAlignment(Pos.CENTER);
        homeMenu.setOnMouseClicked(event -> {
            try {
                new HomeScene(stage).show(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Wishlist Button
        Label wishlistMenu = new Label("WISHLIST");
        wishlistMenu.getStyleClass().add("navbar");
        wishlistMenu.setAlignment(Pos.CENTER);
        wishlistMenu.setOnMouseClicked(event -> {
            System.out.println("wishlist Label diklik!");
        });

        // Orders Button
        Label ordersMenu = new Label("ORDERS");
        ordersMenu.getStyleClass().add("navbar");
        ordersMenu.setAlignment(Pos.CENTER);
        ordersMenu.setOnMouseClicked(event -> {
            System.out.println("order Label diklik!");
        });

        //tombol cari
        // ToolBar searchToolbar = new ToolBar();
        TextField searchBar = new TextField();
        searchBar.setPromptText(getSearchText());
        searchBar.getStyleClass().add("searchBar");
        ImageView searchLogo = imageSet.setImages("/images/navbar/search.png", 20, 20);
        Button searchButton = new Button();
        searchButton.setGraphic(searchLogo);
        searchButton.getStyleClass().add("searchButton"); 
        searchButton.setOnAction(e->{
            setSearchText(searchBar.getText());
            // ke scene lain
        });
        
        searchBar.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                searchButton.fire();
            }
        });

        //Profile
        ImageView profileLogo = imageSet.setImages(user.getPhotoFile(), 40, 40);
        ImageView border = imageSet.setImages("/images/navbar/border.png", 40, 40);
        StackPane imageCombine = new StackPane(profileLogo, border);
        imageCombine.setAlignment(Pos.CENTER_LEFT);
        Button profileMenu = new Button();
        profileMenu.getStyleClass().add("navbarIcon");
        profileMenu.setGraphic(imageCombine);
        profileMenu.setOnAction(e->{
            //ke profile scene
        });

          // Cart Button
        ImageView cartLogo = imageSet.setImages("/images/navbar/cart.png", 60, 40);
        Label cartMenu = new Label();
        cartMenu.getStyleClass().add("navbarIcon");
        cartMenu.setGraphic(cartLogo);
        cartMenu.setOnMouseClicked(e->{
            try {
                new CartScene(stage).show(id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });


        Label cartLabel = new Label("Home");
        cartLabel.getStyleClass().add("navbar");
        cartLabel.setOnMouseClicked(event -> {
            System.out.println("Home Label diklik!");
        });


        
        HBox menu = new HBox(homeMenu, wishlistMenu, ordersMenu, searchBar, searchButton, profileMenu, cartMenu);
        menu.setSpacing(0);
        menu.setAlignment(Pos.TOP_CENTER);
        // menu.setPadding(new Insets(5, 0, 0, 0));
        StackPane menuBar = new StackPane(navbarLabel, menu);


        VBox navbar = new VBox(brandLayout, menuBar);
        return navbar;







    }
}
