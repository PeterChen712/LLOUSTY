package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Category;
import llousty.Models.Comment;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.RateMaker;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.CommentsController;
import llousty.controller.ProductController;
import llousty.controller.UserController;
import llousty.controller.CartController;
import llousty.controller.CategoryController;

public class ProductScene implements ShowScene{
    private int count = 1; // Jumlah produk yang akan dibeli
        

    private Stage stage;

    public ProductScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId, int productId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        User user = UserController.getUserById(userId);
        Product product = ProductController.getProductById(productId);
        List<Comment> comments = CommentsController.getAllCommentByProductId(productId);
        List<Category> categorys = CategoryController.getAllCategoryByProductId(productId);

        ImageView productPhoto = imageSet.setImages(product.getProductPhoto(), 300, 500);
        productPhoto.setPreserveRatio(true);
        


        Label productName = new Label(product.getName());
        productName.getStyleClass().add("productName");


        User seller = UserController.getUserById(product.getSellerId());
        ImageView profileLogo = imageSet.setImages(seller.getPhotoFile(), 40, 40);
        ImageView border = imageSet.setImages("/images/product/border.png", 40, 40);
        StackPane imageCombine = new StackPane(profileLogo, border);
        imageCombine.setAlignment(Pos.CENTER_LEFT);
        Button profileMenu = new Button();
        profileMenu.getStyleClass().add("profileMenu");
        profileMenu.setGraphic(imageCombine);
        profileMenu.setOnAction(e->{
            //ke profile scene
            try {
                new ProfileScene(stage).show(product.getSellerId(), userId);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        Label profileName = new Label();
        if (userId == product.getSellerId()) {
            profileName.setText(seller.getName() + "\nYou own this product");
        }
        else{
        profileName.setText(seller.getName());
        }

        HBox sellerBox = new HBox(profileMenu, profileName);

        Label rating = new Label(RateMaker.calculateRatingAverage(product.getTotalRate(), product.getRate()) + "  (" + product.getTotalRate() + ")");

        Label stockAvalible = new Label("Stock : " + product.getStock());

        TextArea descriptionText = new TextArea(product.getDescription());
        descriptionText.getStyleClass().add("descriptionText");
        descriptionText.setWrapText(true); // Mengaktifkan pemindahan kata otomatis ke baris baru
        descriptionText.setPrefWidth(400); // Mengatur lebar maksimum TextArea
        descriptionText.setEditable(false);

        

        Label categoryLabel = new Label();
        categoryLabel.getStyleClass().add("variantLabel");
        categoryLabel.setText("category : " + product.getCategory());
        // if (categorys.size() != 0) {
        //     for (Category category : categorys) {
        //         Label categoryType = new Label(String.valueOf(category.getCategory()));
        //         categoryType.getStyleClass().add("variantType");
        //         categoryType.setPrefWidth(50);
        //         categoryType.setPrefHeight(20);
        //     }
        // }
        // else{
        //     categoryLabel.setText("No category available");
        // }


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
        double price = product.getPrice() * count;
        addToCart.setText("Add to Cart");

        addToCart.setOnMouseClicked(e -> {
            if (userId == product.getSellerId()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Can't buy your own product");
                alert.setHeaderText(null);
                alert.setContentText("You can't buy your own product.");
                alert.showAndWait();
            }
            else if (product.getStock() < count) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insufficient Stock");
                alert.setHeaderText(null);
                alert.setContentText("The product stock is not enough to fulfill your request.");
                alert.showAndWait();
            }
            else{
                CartController.addCart(productId, userId, product.getCategory(), count, price);
                try {
                    new CartScene(stage).show(userId);
                } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                        | InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
    

        VBox information = new VBox(productName, sellerBox, rating, stockAvalible, descriptionText, categoryLabel, quantity, addToCart);

        HBox element1 = new HBox(productPhoto, information);
        element1.setSpacing(10);
        element1.setPrefWidth(750);
        element1.getStyleClass().add("mainContent");

        //COMENT

        Label labelTitleComments = new Label("Comments");
        labelTitleComments.getStyleClass().add("labelTitleComments");
        labelTitleComments.setPadding(new Insets(10, 0, 0, 0));

        VBox vBoxBookComments = new VBox();
        vBoxBookComments.setSpacing(7);

        if (comments.size() > 0) {
            for (Comment comment : comments) {
                User commentUser = UserController.getUserById(comment.getUserId());
                Label labelName = new Label(commentUser.getName());
                labelName.getStyleClass().add("userKomen");

                Label labelText = new Label(comment.getText());
                VBox vBoxComment = new VBox(labelName, labelText);
                vBoxBookComments.getChildren().add(vBoxComment);
            }
        } else {
            Label labelNoComment = new Label("No comment");
            vBoxBookComments.getChildren().add(labelNoComment);
        }

        
        Label labelPostComment = new Label("Write a comment");
        labelPostComment.setStyle( "-fx-text-fill: #9f4168; -fx-font-size: 13px;");
        TextField textFieldComment = new TextField();
        textFieldComment.setPromptText("Write your comment");
        textFieldComment.setPrefHeight(50);
        Button buttonPostComment = new Button("Sent comment");
        buttonPostComment.getStyleClass().add("addComment");
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
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });



        //MAIN 

        ScrollPane scrollPane = new ScrollPane(vBoxMainContent);
        scrollPane.setFitToWidth(true);
        
        VBox profileRoot = new VBox(Navbar.getNavbar(stage, userId), scrollPane);


        Scene scene = new Scene(profileRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void show() throws InterruptedException {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
