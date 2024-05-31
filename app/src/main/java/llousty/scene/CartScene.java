package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Models.Discount;
import llousty.Utils.DateGenerator;
import llousty.Utils.RemoveIndex;
import llousty.Utils.imageSet;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.CartController;
import llousty.controller.ProductController;
import llousty.controller.TransactionController;
import llousty.controller.UserController;
import llousty.controller.DiscountController;

public class CartScene implements ShowScene{
    private double total;
    private double deliveryTax = 30000;
    private List<Product> listProductPurschased = new ArrayList<>();



    private String choosedPayment = null;
    public String getChoosedPayment() {
        return choosedPayment;
    }
    public void setChoosedPayment(String choosedPayment) {
        this.choosedPayment = choosedPayment;
    }

    private Stage stage;

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
            userInformation.setPadding(new Insets(5, 0, 0, 25));
    
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
    
            Label subTotal = new Label("Subtotal : Rp" + priceFormatter.formatCurrency(total));
            Label delivery = new Label("Delivery : Rp" + priceFormatter.formatCurrency(deliveryTax));
            Label totalUserPay = new Label("Total : Rp" + priceFormatter.formatCurrency(total + deliveryTax));
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
                else{
                    for (Product product : listProductPurschased) {
                        User seller;
                        try {
                            seller = UserController.getUserById(product.getSellerId());
                            if (TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), (product.getPrice()), getChoosedPayment(), product.getName(), seller.getName(), "Pending")) {
                                System.out.println("yess");
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    TransactionController.addTransaction(userId, DateGenerator.getCurrentDateTimeAsString(), deliveryTax, getChoosedPayment(), "Delivery Tax", "", "Pending");
    
    
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
            VBox transactionBar = new VBox(20, userInformation, paymentMethod, paddingPay);
            
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
                Label productName = new Label(product.getName() + " (×" + cart.getTotalAmount() + ")");
                productName.getStyleClass().add("productNameCart");
                Label sellerName = new Label(seller.getName());
                sellerName.getStyleClass().add("sellerName");
    
                Label discountLabel = new Label();
                discountLabel.getStyleClass().add("variantLabelCart");
                Label discountType = new Label();
                if (cart.getVariantId() != 0) {
                    Discount discount = DiscountController.getDiscountById(cart.getVariantId());
                    discountType.setText(String.valueOf(discount.getDiscount()));
                    discountType.getStyleClass().add("variantTypeCart");
                    discountLabel.setText("Discount : ");
                }
                else{
                }
                productImage = imageSet.setImages(product.getProductPhoto(), 80, 80);
                HBox variantBar = new HBox(discountLabel, discountType);
                
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
                    }
                    else{
                        total -= cart.getPrice() * cart.getTotalAmount();
                        RemoveIndex.removeOne(listProductPurschased, product);
                    }
                    subTotal.setText("Subtotal : Rp" + priceFormatter.formatCurrency(total));
                    totalUserPay.setText("Total : Rp" + priceFormatter.formatCurrency(total + deliveryTax));
                });
    
                HBox cartItems = new HBox(10, information1, information2);
                cartLayout.getChildren().addAll(gap, cartItems);
            }
    
            ScrollPane scrollPane = new ScrollPane(cartLayout);
            scrollPane.setFitToWidth(true);
    
            HBox cartDisplay = new HBox(scrollPane, transactionFix);
    
    
    
    
    
    
    
            // Product product = ProductController.getProductById(productId);
    
            
    
            
    
    
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
