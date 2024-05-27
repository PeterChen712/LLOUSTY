package llousty.scene;

import java.sql.SQLException;
import java.util.List;

import org.checkerframework.checker.units.qual.min;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Comment;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Models.Variant;
import llousty.Utils.RateMaker;
import llousty.Utils.imageSet;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.CommentsController;
import llousty.controller.ProductController;
import llousty.controller.UserController;
import llousty.controller.VariantController;

public class ProductScene {
    private int count = 1; 

    private Stage stage;

    public ProductScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId, int productId) throws SQLException{
        User user = UserController.getUserById(userId);
        Product product = ProductController.getProductById(productId);
        List<Comment> comments = CommentsController.getAllCommentByProductId(productId);
        List<Variant> variants = VariantController.getAllVariantByProductId(productId);

        ImageView productPhoto = imageSet.setImages(product.getProductPhoto(), 300, 500);
        productPhoto.setPreserveRatio(true);
        


        Label productName = new Label(product.getName());
        productName.getStyleClass().add("productName");
        // ImageView star = imageSet.setImages("/images/product/image.png", 5, 5);
        // HBox starBar = new HBox();
        // for (int i = 0; i < 5; i++) {
        //     double starCount = product.getRate() - i;
        //     if (starCount > 0) {
        //         starBar.getChildren().add(star);
        //     }
        // }


        ImageView profileLogo = imageSet.setImages(user.getPhotoFile(), 40, 40);
        ImageView border = imageSet.setImages("/images/product/border.png", 40, 40);
        StackPane imageCombine = new StackPane(profileLogo, border);
        imageCombine.setAlignment(Pos.CENTER_LEFT);
        Button profileMenu = new Button();
        profileMenu.getStyleClass().add("profileMenu");
        profileMenu.setGraphic(imageCombine);
        profileMenu.setOnAction(e->{
            //ke profile scene
        });

        User seller = UserController.getUserById(product.getSellerId());
        Label profileName = new Label();
        if (userId == product.getSellerId()) {
            profileName.setText(seller.getName() + "\nYou own this product");
        }
        else{
        profileName.setText(seller.getName());
        }

        HBox sellerBox = new HBox(profileMenu, profileName);

        Label rating = new Label(RateMaker.calculateRatingAverage(product.getTotalRate(), product.getRate()));

        TextArea descriptionText = new TextArea(product.getDescription());
        descriptionText.getStyleClass().add("descriptionText");
        descriptionText.setWrapText(true); // Mengaktifkan pemindahan kata otomatis ke baris baru
        descriptionText.setPrefWidth(400); // Mengatur lebar maksimum TextArea
        descriptionText.setEditable(false);

        // GridPane gridPaneVariant = new GridPane();
        // gridPaneVariant.setHgap(1);
        // gridPaneVariant.setVgap(1);
        // int column = 0;
        // int row = 0;
        

        Label variantLabel = new Label();
        variantLabel.getStyleClass().add("variantLabel");
        if (variants.size() != 0) {
            for (Variant variant : variants) {
                variantLabel.setText("variant");
                Label variantType = new Label(variant.getVariantName());
                variantType.getStyleClass().add("variantType");
                variantType.setPrefWidth(50);
                variantType.setPrefHeight(20);
                variantType.setOnMouseClicked(e->{
                // productPhoto = imageSet.setImages(variant.getVariantPhoto(), 300, 500);
                System.out.println("Helo");
            });



                // gridPaneVariant.add(variantLabel, column, row);

                // column++;
                // if (column == 4) {
                //     column = 0;
                //     row++;
                // }
                // break;
            }
        }
        else{
            variantLabel.setText("no variant");
        }


        Label countLabel = new Label(String.valueOf(count));
        countLabel.getStyleClass().add("countLabel");

        Label minusLabel = new Label();
        minusLabel.setGraphic(imageSet.setImages("/images/product/minus.png", 30, 30));
        minusLabel.getStyleClass().add("quantity");
        minusLabel.setOnMouseClicked(e -> {
            if (count > 1) {
                count--;
                countLabel.setText(String.valueOf(count));
            }
        });

        Label plusLabel = new Label();
        plusLabel.setGraphic(imageSet.setImages("/images/product/plus.png", 30, 30));
        plusLabel.getStyleClass().add("quantity");
        plusLabel.setOnMouseClicked(e -> {
            count++;
            countLabel.setText(String.valueOf(count));
        });

        HBox quantity = new HBox(minusLabel, countLabel, plusLabel);

        Label addToCart = new Label();
        addToCart.getStyleClass().add("addToCart");
        // double price = product.getPrice() * count;
        addToCart.setText("Add to Cart");





        VBox information = new VBox(productName, sellerBox, rating, descriptionText, variantLabel, quantity, addToCart);
        
        



        HBox element1 = new HBox(productPhoto, information);
        element1.setSpacing(10);
        element1.setPrefWidth(750);
        element1.getStyleClass().add("mainContent");

        //COMENT

        Label labelTitleComments = new Label("Comment");
        labelTitleComments.getStyleClass().add("labelTitleComments");
        labelTitleComments.setPadding(new Insets(10, 0, 0, 0));

        VBox vBoxBookComments = new VBox();
        vBoxBookComments.setSpacing(7);

        if (comments.size() > 0) {
            for (Comment comment : comments) {
                User commentUser = UserController.getUserById(comment.getUserId());
                Label labelName = new Label(commentUser.getName());

                Label labelText = new Label(comment.getText());
                VBox vBoxComment = new VBox(labelName, labelText);
                vBoxBookComments.getChildren().add(vBoxComment);
            }
        } else {
            Label labelNoComment = new Label("No comment");
            vBoxBookComments.getChildren().add(labelNoComment);
        }

        
        Label labelPostComment = new Label("Write a comment");
        TextField textFieldComment = new TextField();
        textFieldComment.setPromptText("Write your comment");
        textFieldComment.setPrefHeight(50);
        Button buttonPostComment = new Button("Add");
        VBox vBoxPostComment = new VBox(labelPostComment, textFieldComment, buttonPostComment);
        vBoxPostComment.setSpacing(5);

        VBox vBoxComments = new VBox(labelTitleComments, vBoxBookComments, vBoxPostComment);
        vBoxComments.setSpacing(10);

        VBox vBoxMainContent = new VBox(element1, vBoxComments);
        vBoxMainContent.setPadding(new Insets(10));
        vBoxMainContent.setSpacing(15);
        vBoxMainContent.setPrefWidth(620);

        buttonPostComment.setOnAction(e->{
            String text = textFieldComment.getText();
            if (text != null && !text.isEmpty()) {
                // Add comment
                if (CommentsController.addComment(text, user.getId(), productId, 0)) {
                    // Refresh the scene
                    try {
                        new ProductScene(stage).show(userId, productId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });



        //MAIN 

        ScrollPane scrollPane = new ScrollPane(vBoxMainContent);
        scrollPane.setFitToWidth(true);
        
        Navbar navbar = new Navbar();
        VBox profileRoot = new VBox(navbar.getNavbar(stage, userId), scrollPane);


        Scene scene = new Scene(profileRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
