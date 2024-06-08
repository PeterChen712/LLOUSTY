package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Cart;
import llousty.Models.Category;
import llousty.Models.History;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.DateGenerator;
import llousty.Utils.RemoveIndex;
import llousty.Utils.imageSet;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.CartController;
import llousty.controller.CategoryController;
import llousty.controller.HistoryController;
import llousty.controller.NotifController;
import llousty.controller.ProductController;
import llousty.controller.TransactionController;
import llousty.controller.UserController;

public class CartScene implements ShowScene{
    private double total;
    private double deliveryTax = 30000;
    private List<Product> listProductPurschased = new ArrayList<>();
    private List<Cart> listCartSelected = new ArrayList<>();
    private List<History> listHistoryForSeller = new ArrayList<>();

    private static double newRating;
    private static double selectedRating = 0;
    private static double previousRating;
        
    // private static void updateRating(int rating) {
    //     selectedRating = rating + previousRating;
    // }

    private static void updateRating(int rating) {
        selectedRating = rating;
    }

    // private static void refreshStars(HBox ratingBox, double rating) {
    //     for (Node node : ratingBox.getChildren()) {
    //         if (node instanceof ImageView) {
    //             ImageView star = (ImageView) node;
    //             star.getStyleClass().removeAll("rated", "unrated");
    //             if (star.getId().replaceAll("star-", "").equals(String.valueOf((int) rating))) {
    //                 star.getStyleClass().add("rated");
    //             } else if (Integer.parseInt(star.getId().replaceAll("star-", "")) <= rating) {
    //                 star.getStyleClass().add("rated");
    //             } else {
    //                 star.getStyleClass().add("unrated");
    //             }
    //         }
    //     }
    // }


    // private static void showRatingPopup(int userId, List<Product> purchasedProducts) {
    //     for (Product product : purchasedProducts) {
    //         Stage ratingStage = new Stage();
    //         ratingStage.initOwner(stage);
    //         ratingStage.setTitle("Rate this Product");
    
    //         Label ratingLabel = new Label("Please rate this product:");
    //         ratingLabel.getStyleClass().add("askRating");
    
    //         HBox ratingBox = new HBox(10);
    //         ratingBox.getChildren().clear();
    
    //         double previousRating = product.getRate();
    
    //         for (int i = 1; i <= 5; i++) {
    //             ImageView star = new ImageView("/images/product/star.png");
    //             star.getStyleClass().removeAll("rated", "unrated");
    //             star.getStyleClass().addAll("rating-star", "askRating");
    //             star.setId("star-" + i);
    //             star.setFitWidth(24);
    //             star.setFitHeight(24);
    //             newRating = i;
    //             star.setOnMouseClicked(event -> {
    //                 updateRating((int)newRating);
    //             });
    //             ratingBox.getChildren().add(star);
    //         }
    //         refreshStars(ratingBox, selectedRating);
    
    //         Button rateButton = new Button("Rate");
    //         Button cancelButton = new Button("Cancel");
    //         rateButton.getStyleClass().add("askRating");
    //         cancelButton.getStyleClass().add("askRating");
    
    //         HBox buttonBox = new HBox(10, rateButton, cancelButton);
    //         VBox ratingPane = new VBox(20, ratingLabel, ratingBox, buttonBox);
    //         ratingPane.getStyleClass().add("askRating");
    
    //         Scene ratingScene = new Scene(ratingPane);
    //         ratingScene.getStylesheets().add("styles.css");
    //         ratingStage.setScene(ratingScene);
    
    //         rateButton.setOnAction(rateEvent -> {
    //             // Memperbaharui rating produk
    //             System.out.println("Selected Rating: " + selectedRating);
    //             newRating = product.getRate() + selectedRating;
    //             // int totalRate = product.getTotalRate();
    //             ProductController.updateProductRating(product.getId(), newRating, product.getTotalRate() + 1);
    //             ratingStage.close();
    //         });
    
    //         cancelButton.setOnAction(cancelEvent -> ratingStage.close());
    
    //         ratingStage.showAndWait();
    //     }
    // }



    private static void showRatingPopup(int userId, List<Cart> listCartSelected) {
        Set<Product> uniqueProducts = new HashSet<>();
        for (Cart cart : listCartSelected) {
            Product product = ProductController.getProductById(cart.getProductId());
            uniqueProducts.add(product);
        }
        
        for (Product product : uniqueProducts) {
            System.out.println("Total :" + uniqueProducts.size());
            Stage ratingStage = new Stage();
            ratingStage.initOwner(stage);
            ratingStage.setTitle("Rate this Product");
    
            Label ratingLabel = new Label("Please rate:\n" + product.getName());
            ratingLabel.getStyleClass().addAll("askRating", "title");
            ratingLabel.setAlignment(Pos.CENTER);
            ratingLabel.setWrapText(true);
            ratingLabel.setMaxWidth(300);
            ratingLabel.setStyle("-fx-font-size: 18px;"); // Mengurangi ukuran font judul
    
            HBox ratingBox = new HBox(10); // Mengurangi jarak antar tombol rating
            ratingBox.getChildren().clear();
            ratingBox.setAlignment(Pos.CENTER);
    
            for (int i = 1; i <= 5; i++) {
                Button ratingButton = new Button(String.valueOf(i));
                ratingButton.getStyleClass().add("rating-button");
                ratingButton.setId("rating-" + i);
                ratingButton.setPrefSize(40, 40); 
                final int rating = i;
                ratingButton.setOnAction(event -> {
                    updateRating(rating);
                    refreshRatingButtons(ratingBox, rating);
                });
                ratingBox.getChildren().add(ratingButton);
            }
    
            Button rateButton = new Button("Rated");
            Button cancelButton = new Button("Cancel");
            rateButton.getStyleClass().addAll("askRating", "button", "rate-button");
            cancelButton.getStyleClass().addAll("askRating", "button", "cancel-button");
            rateButton.setPrefSize(150, 40); // Mengurangi ukuran tombol "Rate"
            cancelButton.setPrefSize(150, 40); // Mengurangi ukuran tombol "Cancel"
    
            HBox buttonBox = new HBox(20, cancelButton, rateButton);
            buttonBox.setAlignment(Pos.CENTER);
            VBox ratingPane = new VBox(20, ratingLabel, ratingBox, buttonBox); // Mengurangi padding VBox
            ratingPane.getStyleClass().add("askRating");
            ratingPane.setAlignment(Pos.TOP_CENTER);
            ratingPane.setPadding(new Insets(20)); // Mengurangi padding VBox
    
            Scene ratingScene = new Scene(ratingPane, 400, 300); // Ukuran scene yang lebih proporsional
            ratingScene.getStylesheets().add("styles.css");
            ratingStage.setScene(ratingScene);




            rateButton.setOnAction(rateEvent -> {
                System.out.println("Selected Rating: " + selectedRating);
                newRating = (product.getRate() * product.getTotalRate() + selectedRating) / (product.getTotalRate() + 1);
                ProductController.updateProductRating(product.getId(), newRating, product.getTotalRate() + 1);
                ratingStage.close();
            });
    
            cancelButton.setOnAction(cancelEvent -> ratingStage.close());
    
            ratingStage.showAndWait();
        }
    }



    
    private static void refreshRatingButtons(HBox ratingBox, int selectedRating) {
        for (Node node : ratingBox.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int rating = Integer.parseInt(button.getId().replaceAll("rating-", ""));
                button.getStyleClass().removeAll("selected", "unselected");
                if (rating <= selectedRating) {
                    button.getStyleClass().add("selected");
                } else {
                    button.getStyleClass().add("unselected");
                }
            }
        }
    }




    private String choosedPayment = null;

    public String getChoosedPayment() {
        return choosedPayment;
    }
    public void setChoosedPayment(String choosedPayment) {
        this.choosedPayment = choosedPayment;
    }

    private static Stage stage;

    public CartScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        User user = UserController.getUserById(userId);
        List<Cart> carts = CartController.getAllCartByUserId(userId);
        if (carts.size() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cart is Empty");
            alert.setHeaderText(null);
            alert.setContentText("Please add items to your cart before proceeding.");
            alert.showAndWait();
        }
        else{

            // List<Product> products = ProductController.getProductById
    
            TextArea userInformation = new TextArea(user.getName() + "\n" + user.getPhone() + "\n" + user.getAlamat());
            userInformation.getStyleClass().add("userInformation");
            userInformation.setEditable(false);
            userInformation.setPadding(new Insets(20, 0, 0, 25));
    
            Label paymentLabel = new Label("Payment Method");
            paymentLabel.getStyleClass().add("paymentLabel");
    
            Label eMoney = new Label("E-Money");
            eMoney.getStyleClass().add("payment");
            Label virtualAccount = new Label("Virtual Account");
            virtualAccount.getStyleClass().add("payment");
            Label credit = new Label("Credit Card");
            credit.getStyleClass().add("payment");
            Label cod = new Label("COD");
            cod.getStyleClass().add("payment");
    
            eMoney.setOnMouseClicked(e -> {
                eMoney.setStyle("-fx-background-color: #DEA0BC;");
                virtualAccount.setStyle("-fx-background-color: #D9D9D9;");
                credit.setStyle("-fx-background-color: #D9D9D9;");
                cod.setStyle("-fx-background-color: #D9D9D9;");
                setChoosedPayment("E-Money");
            });
            
            virtualAccount.setOnMouseClicked(e -> {
                eMoney.setStyle("-fx-background-color: #D9D9D9;");
                virtualAccount.setStyle("-fx-background-color: #DEA0BC;");
                credit.setStyle("-fx-background-color: #D9D9D9;");
                cod.setStyle("-fx-background-color: #D9D9D9;");
                setChoosedPayment("Virtual Account");
            });
            
            credit.setOnMouseClicked(e -> {
                eMoney.setStyle("-fx-background-color: #D9D9D9;");
                virtualAccount.setStyle("-fx-background-color: #D9D9D9;");
                credit.setStyle("-fx-background-color: #DEA0BC;");
                cod.setStyle("-fx-background-color: #D9D9D9;");
                setChoosedPayment("Credit Card");
            });
            
            cod.setOnMouseClicked(e -> {
                eMoney.setStyle("-fx-background-color: #D9D9D9;");
                virtualAccount.setStyle("-fx-background-color: #D9D9D9;");
                credit.setStyle("-fx-background-color: #D9D9D9;");
                cod.setStyle("-fx-background-color: #DEA0BC;");
                setChoosedPayment("COD");
            });
    
            
    
    
    
            Label orderText = new Label("Summary");
            orderText.getStyleClass().add("paymentLabel");
    
            Label subTotal = new Label("Subtotal\t\t\t\t\tRp" + priceFormatter.formatCurrency(total));
            subTotal.getStyleClass().add("choosedPayment");
            Label delivery = new Label("Delivery\t\t\t\t\tRp" + priceFormatter.formatCurrency(deliveryTax));
            delivery.getStyleClass().add("choosedPayment");
            Label totalUserPay = new Label("Total\t\t\t\tRp" + priceFormatter.formatCurrency(total + deliveryTax));
            totalUserPay.getStyleClass().add("totalUserPay");

            VBox paymentMethod = new VBox(paymentLabel, eMoney, virtualAccount, credit, cod, orderText, subTotal, delivery, totalUserPay);
            paymentMethod.setPadding(new Insets(0, 0, 0, 20));
            paymentMethod.getStyleClass().add("paymentMethod");
    
            Button pay = new Button("BUY");
            pay.getStyleClass().add("pay");
            pay.setOnAction(e->{
                if (getChoosedPayment() == null ) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Payment Method Not Selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a payment method before proceeding.");
                    alert.showAndWait();
                }
                else if (user.getAlamat() == null && user.getPhone() == null){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("User Information Not Complete");
                    alert.setHeaderText(null);
                    alert.setContentText("Please complete your user information before proceeding.");
                    alert.showAndWait();
                } 
                else if (listCartSelected.size() == 0){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("No Item Selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an item before proceeding.");
                    alert.showAndWait();
                }
                else{
                    boolean isSuccessful = false;
                    for (Product product : listProductPurschased) {
                        User seller;
                        try {
                            seller = UserController.getUserById(product.getSellerId());
                            if (getChoosedPayment().equals("COD")) {
                                isSuccessful = TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), (product.getPrice()), getChoosedPayment(), product.getName(), seller.getName(), "Pending");
                            } else {
                                isSuccessful = TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), (product.getPrice()), getChoosedPayment(), product.getName(), seller.getName(), "Succeed");
                            }

                            if (isSuccessful) {
                                for (Cart cart : listCartSelected) {
                                    System.out.println("cart id: " + String.valueOf(listCartSelected));
                                    // Cart cartForTotalPurchased = CartController.getCartById(cart.getId());
                                    // System.out.println("Cart name :" + cartForTotalPurchased.getProductId());

                                    ProductController.updateProductStock(product.getId(), product.getStock() - cart.getTotalAmount());
                                    
                                    HistoryController.createHistory(product.getSellerId(), user.getId(), product.getName(), cart.getTotalAmount(), cart.getPrice() * cart.getTotalAmount());
                                    NotifController.addNotif("Payment Successful", "Your payment has been successfully processed. Thank you for your purchase!", userId, DateGenerator.getCurrentDateTimeAsString(), "accept");
                                    NotifController.addNotif("New Purchase", "Your product has been purchased by " + user.getName(), seller.getId(), DateGenerator.getCurrentDateTimeAsString(), "information");
                                    
                                    
                                    
                                    System.out.println(listProductPurschased.size());
                                    CartController.deleteCartById(cart.getId());
                                    }
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if (isSuccessful) {
                        showRatingPopup(userId, listCartSelected);
                        TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), deliveryTax, "", "Delivery Tax", "", "Succeed");
                        try {
                            new TransactionScene(stage).show(userId);
                        } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                                | InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
    
    
                    // if (TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), (total + deliveryTax), getChoosedPayment(), StringListConverter.listToString(listProductName))) {
                    //     System.out.println("yess");
                    //     try {
                    //         new TransactionScene(stage).show(userId);
                    //     } catch (SQLException e1) {
                    //         e1.printStackTrace();
                    //     }
                    // }
                    
                }
            });
            HBox paddingPay = new HBox(pay);
            paddingPay.setPadding(new Insets(0, 0, 0, 75));
            VBox transactionBar = new VBox(30, userInformation, paymentMethod, paddingPay);
            
            Label transactionBarBg = new Label();
            transactionBarBg.getStyleClass().add("transactionBarBg");
    
            StackPane transactionFix = new StackPane(transactionBarBg, transactionBar);
    
            
            
    
            VBox cartLayout = new VBox();
            cartLayout.getStyleClass().add("cartLayout");
    
            for (Cart cart : carts) {
                Product product = ProductController.getProductById(cart.getProductId());
                User seller = UserController.getUserById(product.getSellerId());
                
                CheckBox checkBox = new CheckBox();
                checkBox.getStyleClass().add("custom-checkbox");
                checkBox.setAlignment(Pos.CENTER);
                checkBox.setPadding(new Insets(30, 0, 0, 15));
                
    
                
                ImageView productImage;
                Label productName = new Label(product.getName() + " (x" + cart.getTotalAmount() + ")");
                productName.getStyleClass().add("productNameCart");
                Label sellerName = new Label(seller.getName());
                sellerName.getStyleClass().add("sellerName");
    
                Label categoryLabel = new Label();
                categoryLabel.getStyleClass().add("variantLabelCart");
                Label categoryType = new Label();
                if (cart.getCategory() != null) {
                    // Category category = CategoryController.getCategoryById(cart.getCategoryId());
                    categoryType.setText(product.getCategory());
                    categoryType.getStyleClass().add("variantTypeCart");
                    categoryLabel.setText("category : ");
                }
                else{
                }
                productImage = imageSet.setImages(product.getProductPhoto(), 80, 80);
                HBox variantBar = new HBox(categoryLabel, categoryType);
                
                productImage.setOnMouseClicked(e->{
                    try {
                        new ProductScene(stage).show(userId, product.getId());
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
                Label totalPrice = new Label("Rp" + priceFormatter.formatCurrency(cart.getPrice() * cart.getTotalAmount()));
                totalPrice.getStyleClass().add("totalPrice");
    
                Label gap = new Label();
                gap.getStyleClass().add("gap");
                HBox information1 = new HBox(10, checkBox, productImage);
                VBox information2 = new VBox(productName, sellerName, variantBar, totalPrice);
    
                
                
                checkBox.setOnAction(e->{
                    boolean isSelected = checkBox.isSelected();
                    if (isSelected) {
                        total += cart.getPrice() * cart.getTotalAmount();
                        listProductPurschased.add(product);
                        listCartSelected.add(cart);
                        // listHistoryForSeller.add(new History(product.getId(), userId, cart.getTotalAmount()));
                    }
                    else{
                        total -= cart.getPrice() * cart.getTotalAmount();
                        RemoveIndex.removeOne(listProductPurschased, product);
                        RemoveIndex.removeOne(listCartSelected, cart);
                    }
                    subTotal.setText("Subtotal\t\t\t\t\tRp" + priceFormatter.formatCurrency(total));
                    totalUserPay.setText("Total\t\t\t\tRp" + priceFormatter.formatCurrency(total + deliveryTax));
                });
    
                HBox cartItems = new HBox(10, information1, information2);
                cartLayout.getChildren().addAll(gap, cartItems);
            }
    
            ScrollPane scrollPane = new ScrollPane(cartLayout);
            scrollPane.setFitToWidth(true);
    
            HBox cartDisplay = new HBox(10, scrollPane, transactionFix);
    
            //MAIN 
    
    
            VBox cartRoot = new VBox(Navbar.getNavbar(stage, userId), cartDisplay);
    
    
            Scene scene = new Scene(cartRoot, App.getWidth(), App.getHeight());
            scene.getStylesheets().add("styles.css");
            stage.setScene(scene);
            stage.show();
        }
    }
    @Override
    public void show() {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
