package llousty.scene;

import java.sql.SQLException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.SearchEngine;
import llousty.Utils.imageSet;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.ProductController;
import llousty.controller.UserController;

public class SearchScene {
    private Stage stage;
    public SearchScene(Stage stage) {
        this.stage = stage;
    }

    private static GridPane showProductSearch(Stage stage, String filter, int id) throws SQLException{
        List<Product> listProducts = ProductController.getAllProduct();

        GridPane productLayout = new GridPane();
        productLayout.setHgap(24);
        productLayout.setVgap(10);
        productLayout.setPadding(new Insets(0, 0, 0, 24));

        int column = 0;
        int row = 0;

        for (Product product : listProducts) {
            if (SearchEngine.containsSearchName(product.getName(), filter)) { 
                
                ImageView productImage = imageSet.setImages(product.getProductPhoto(), 150, 150);
                productImage.setPreserveRatio(true);
    
                Label productBackground = new Label();
                productBackground.getStyleClass().add("productBackground");
                
                StackPane productView = new StackPane(productBackground, productImage);
    
    
                Label productName = new Label(product.getName());
                productName.getStyleClass().add("productName");
                Label productPrice = new Label("Rp." + priceFormatter.formatCurrency(product.getPrice()));
                productPrice.getStyleClass().add("productPrice");
                
    
                VBox productInfo = new VBox(productView, productName, productPrice);
                productInfo.setSpacing(10);
    
                VBox productBox = new VBox(productImage, productInfo);
                productBox.getStyleClass().add("productBox");
                productBox.setOnMouseClicked(e->{
                    try {
                        new ProductScene(stage).show(id, product.getId());
                    } catch (SQLException e1) {
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
            else{
                ImageView notFoundImage = imageSet.setImages("/images/default/nullNotFound.png", 350, 345);
                Label notfound = new Label();
                notfound.setGraphic(notFoundImage);
                HBox makeCenter = new HBox(notfound);
                makeCenter.setPadding(new Insets(0, 0, 0, 200));
                productLayout.getChildren().add(makeCenter);
            }
        }
        return productLayout;
    }

    private static VBox showUserSearch(Stage stage, String filter, int id) throws SQLException{
        List<User> users = UserController.getAllUsers();
        VBox profileLayout = new VBox();
        for (User user : users) {
            if (SearchEngine.containsSearchName(user.getName(), filter) || SearchEngine.containsSearchName(user.getUsername(), filter)) { 
                ImageView profileLogo = imageSet.setImages(user.getPhotoFile(), 40, 40);
                profileLogo.setPreserveRatio(true);
                ImageView border = imageSet.setImages("/images/navbar/border.png", 40, 40);
                StackPane imageCombine = new StackPane(profileLogo, border);
                imageCombine.setAlignment(Pos.CENTER_LEFT);
                Button profileMenu = new Button();
                profileMenu.getStyleClass().add("navbarIcon");
                profileMenu.setGraphic(imageCombine);

                profileMenu.setOnAction(e->{
                    //ke profile scene
                    System.out.println("gg bang");
                });
                System.out.println("ke sini");
    
    
                Label profileName = new Label(user.getName());
                profileName.getStyleClass().add("productNameCart");
                Label profileUser = new Label(user.getUsername());
                profileUser.getStyleClass().add("sellerName");
                Label gap = new Label();
                gap.getStyleClass().add("gap2");
                
                VBox profileInfo = new VBox(profileName, profileUser);
                profileInfo.setSpacing(10);

                HBox profileBar = new HBox(10, profileMenu, profileInfo);
                // profileLayout = new VBox(gap, profileBar);
                profileLayout.getChildren().addAll(gap, profileBar);
                
                
            }
            else{
                ImageView notFoundImage = imageSet.setImages("/images/default/nullNotFound.png", 350, 345);
                Label notfound = new Label();
                notfound.setGraphic(notFoundImage);
                HBox makeCenter = new HBox(notfound);
                makeCenter.setPadding(new Insets(0, 0, 0, 223));
                profileLayout = new VBox(makeCenter);
            }
        }
        return profileLayout;
    }

    public void show(int userId) throws SQLException{    
        Navbar navbar = new Navbar();


        Label productLabel = new Label("Product");
        productLabel.getStyleClass().add("searchLabel");
        Label userLabel = new Label("User");
        userLabel.getStyleClass().add("searchLabel");
        HBox filterBar = new HBox(productLabel, userLabel);
        ScrollPane scrollPane = new ScrollPane(showProductSearch(stage, Navbar.getSearchText(), userId));
        // ScrollPane scrollPane = new ScrollPane();
        productLabel.setStyle("-fx-background-color: #DEA0BC;");


        productLabel.setOnMouseClicked(e->{
            try {
                scrollPane.setContent(showProductSearch(stage, Navbar.getSearchText(), userId));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            productLabel.setStyle("-fx-background-color: #DEA0BC;");
            userLabel.setStyle("-fx-background-color: #f0f0f0;");
        });

        userLabel.setOnMouseClicked(e->{
            try {
                scrollPane.setContent(showUserSearch(stage, Navbar.getSearchText(), userId));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            userLabel.setStyle("-fx-background-color: #DEA0BC;");
            productLabel.setStyle("-fx-background-color: #f0f0f0;");
        });






        //Main
        VBox searchRoot = new VBox(navbar.getNavbar(stage, userId), filterBar, scrollPane);


        Scene scene = new Scene(searchRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
