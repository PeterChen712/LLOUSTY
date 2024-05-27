package llousty.scene;

import java.sql.SQLException;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Cart;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.CartController;
import llousty.controller.ProductController;
import llousty.controller.UserController;

public class CartScene {
    private Stage stage;

    public CartScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) throws SQLException{
        User user = UserController.getUserById(userId);
        List<Cart> carts = CartController.getAllCartByUserId(userId);
        // List<Product> products = ProductController.getProductById

        for (Cart cart : carts) {
            Product product = ProductController.getProductById(cart.getProductId());
            User seller = UserController.getUserById(product.getSellerId());

            CheckBox checkBox = new CheckBox();
            checkBox.getStyleClass().add("custom-checkbox");

            ImageView productImage = imageSet.setImages(product.getProductPhoto(), 30, 30);

            Label productName = new Label(product.getName());
            Label sellerName = new Label(seller.getName());
            



            

            

        }







        // Product product = ProductController.getProductById(productId);

        


        //MAIN 

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        Navbar navbar = new Navbar();
        VBox cartRoot = new VBox(navbar.getNavbar(stage, userId), scrollPane);


        Scene scene = new Scene(cartRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
