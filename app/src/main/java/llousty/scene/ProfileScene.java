package llousty.scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Conversation;
import llousty.Models.History;
import llousty.Models.Product;
import llousty.Models.User;
import llousty.Utils.DateGenerator;
import llousty.Utils.ParticipantFormat;
import llousty.Utils.PasswordHasher;
import llousty.Utils.imageSet;
import llousty.Utils.photoUploader;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.ConversationController;
import llousty.controller.HistoryController;
import llousty.controller.NotifController;
import llousty.controller.ProductController;
import llousty.controller.UserController;

public class ProfileScene implements ShowScene{
    private static Stage stage;
    private static File selectedImageFile;

    
    public ProfileScene(Stage stage) {
        this.stage = stage;
    }
    private static boolean sellerMode = false;
    public static boolean isSellerMode() {
        return sellerMode;
    }
    public static void setSellerMode(boolean sellerMode) {
        ProfileScene.sellerMode = sellerMode;
    }

    private static ImageView photoProduct = new ImageView();


    private static VBox showAddProduct(int userId){

        Label informasi = new Label("Product Information");
        informasi.getStyleClass().add("sellerMenuName");

        Label nameLabel = new Label("Product Name");
        nameLabel.getStyleClass().add("labelIMyProfil");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your product name");

        Label priceLabel = new Label("Price");
        priceLabel.getStyleClass().add("labelIMyProfil");
        TextField priceField = new TextField();
        priceField.setPromptText("Rp...");
    

        Label stockLabel = new Label("Stock");
        stockLabel.getStyleClass().add("labelIMyProfil");
        TextField stockField = new TextField();
        stockField.setPromptText("Enter your product stock");


        Label descriptionLabel = new Label("Description");
        descriptionLabel.getStyleClass().add("labelIMyProfil");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Describe your product");
    

        Label categoryLabel = new Label("Category");
        categoryLabel.getStyleClass().add("labelIMyProfil");
        CheckBox makeupCheckBox = new CheckBox("Make Up");
        CheckBox skinCareCheckBox = new CheckBox("Skin Care");
        CheckBox fragranceCheckBox = new CheckBox("Fragrance");

        nameField.getStyleClass().add("textfieldSeller");
        priceField.getStyleClass().add("textfieldSeller");
        stockField.getStyleClass().add("textfieldSeller");
        descriptionField.getStyleClass().add("textfieldSeller");
        makeupCheckBox.getStyleClass().add("custom-checkbox");
        skinCareCheckBox.getStyleClass().add("custom-checkbox");
        fragranceCheckBox.getStyleClass().add("custom-checkbox");
    
        makeupCheckBox.setOnAction(event -> {
            if (makeupCheckBox.isSelected()) {
                skinCareCheckBox.setSelected(false);
                fragranceCheckBox.setSelected(false);
            }
        });
    
        skinCareCheckBox.setOnAction(event -> {
            if (skinCareCheckBox.isSelected()) {
                makeupCheckBox.setSelected(false);
                fragranceCheckBox.setSelected(false);
            }
        });
    
        fragranceCheckBox.setOnAction(event -> {
            if (fragranceCheckBox.isSelected()) {
                makeupCheckBox.setSelected(false);
                skinCareCheckBox.setSelected(false);
            }
        });

        
        
        Button photoButton = new Button("Product Photo");
        photoButton.getStyleClass().add("profileButtonSeller");
        photoProduct.setFitHeight(40);
        photoProduct.setFitWidth(40);
        photoProduct.setPreserveRatio(true);
        photoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                selectedImageFile = file;
                Image image = new Image(file.toURI().toString());
                photoProduct.setImage(image);
            }
        });
        
        Button uploadButton = new Button("Upload");
        uploadButton.getStyleClass().add("uploadPhotoProduct");

        uploadButton.setOnAction(event -> {
            if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || stockField.getText().isEmpty() || descriptionField.getText().isEmpty() || (!makeupCheckBox.isSelected() && !skinCareCheckBox.isSelected() && !fragranceCheckBox.isSelected()) || selectedImageFile == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please fill all the fields");
                alert.showAndWait();
            }
            else{
                String name = nameField.getText();
                double price = priceFormatter.formatCurrency(priceField.getText());
                
                int stock = Integer.parseInt(stockField.getText());
                String description = descriptionField.getText();
                String category = "";
                if (makeupCheckBox.isSelected()) {
                    category = "Make Up";
                } else if (skinCareCheckBox.isSelected()) {
                    category = "Skin Care";
                } else if (fragranceCheckBox.isSelected()) {
                    category = "Fragrance";
                }
                boolean isSuccesfulAdd = ProductController.addProduct(name, description, category, stock, price, selectedImageFile, userId);
                if (isSuccesfulAdd) {
                    String describe = "Product with name " + name + " is now available for sale.";
                    NotifController.addNotif("Product Successfully Added", describe, userId, DateGenerator.getCurrentDateTimeAsString(), "accept");
                }
            }
        });


        VBox photoBox = new VBox(10);
        photoBox.setAlignment(Pos.CENTER);
        photoBox.getChildren().addAll(photoProduct, photoButton);
    
        HBox buttonBox = new HBox(10, photoButton, uploadButton);
        buttonBox.setAlignment(Pos.CENTER);

        nameField.setMaxWidth(200);
        priceField.setMaxWidth(100);
        stockField.setMaxWidth(100);
        descriptionField.setMaxWidth(300);

        HBox photoButtonBox = new HBox(10);
        photoButtonBox.setAlignment(Pos.CENTER_LEFT); 
        photoButtonBox.getChildren().addAll(photoProduct, photoButton);

        GridPane inputGrid = new GridPane();
        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(priceLabel, 0, 1);
        inputGrid.add(priceField, 1, 1);
        inputGrid.add(stockLabel, 0, 2);
        inputGrid.add(stockField, 1, 2);
        inputGrid.add(descriptionLabel, 0, 3);
        inputGrid.add(descriptionField, 1, 3);
        inputGrid.add(categoryLabel, 0, 4);
        HBox categoryBox = new HBox(2, makeupCheckBox, skinCareCheckBox, fragranceCheckBox);
        inputGrid.add(categoryBox, 1, 4);
        inputGrid.add(photoButtonBox, 1, 5); 
        inputGrid.add(uploadButton, 2, 5); 
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));


        VBox root = new VBox(10, informasi, inputGrid);
        root.setPadding(new Insets(20));
        return root;
    }




    private static VBox showYourProduct(int userId) throws SQLException {
        Label yourProductLabel = new Label("Your Products");
        yourProductLabel.getStyleClass().add("sellerMenuName");
    
        TableView<Product> table = new TableView<>();
        table.setEditable(true);
    
        TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setVisible(false);
    
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setName(event.getNewValue());
            updateProductByProduct(product);
        });
    
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setPrice(event.getNewValue());
            updateProductByProduct(product);
        });
        priceCol.setPrefWidth(80);
    
        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setStock(event.getNewValue());
            updateProductByProduct(product);
        });
        stockCol.setPrefWidth(60);
    
        TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(ComboBoxTableCell.forTableColumn("Make Up", "Skin Care", "Fragrance"));
        categoryCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setCategory(event.getNewValue());
            updateProductByProduct(product);
        });
        categoryCol.setPrefWidth(100);
    
        TableColumn<Product, Void> editCol = new TableColumn<>("Edit");
        editCol.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            
            {
                editButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    showEditDialog(product);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setAlignment(Pos.CENTER);
                    setGraphic(editButton);
                }
            }
        });
        editCol.setPrefWidth(60);
    
        TableColumn<Product, Void> deleteCol = new TableColumn<>("Delete");
        deleteCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            {
                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    showDeleteConfirmationDialog(product, getTableView());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setAlignment(Pos.CENTER);
                    setGraphic(deleteButton);
                }
            }
        });
        deleteCol.setPrefWidth(60); 
    
        table.getColumns().addAll(nameCol, priceCol, stockCol, categoryCol, editCol, deleteCol);
    
        double fixedColumnsWidth = priceCol.getPrefWidth() + stockCol.getPrefWidth() + categoryCol.getPrefWidth() + editCol.getPrefWidth() + deleteCol.getPrefWidth();
    
        nameCol.prefWidthProperty().bind(table.widthProperty().subtract(fixedColumnsWidth).subtract(2)); // 2 pixels for border
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        List<Product> products = ProductController.getAllProductBySellerId(userId);
        table.getItems().addAll(products);
    
        VBox vbox;
        if (products.isEmpty()) {
            Label noProductLabel = new Label("You don't have any products yet.");
            noProductLabel.getStyleClass().add("no-product-label");
    
            Button returnToUserModeButton = new Button("Return to User Mode");
            returnToUserModeButton.getStyleClass().add("return-user-mode-button");
            returnToUserModeButton.setOnAction(e -> {
                try {
                    UserController.updateUserSellerMode(userId, "User");
                    setSellerMode(false);
                    new ProfileScene(stage).show(userId, userId);
                } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
    
            vbox = new VBox(10, yourProductLabel, noProductLabel, returnToUserModeButton);
            vbox.setAlignment(Pos.CENTER);
        } else {
            vbox = new VBox(10, yourProductLabel, table);
        }
        vbox.setPadding(new Insets(20, 0, 0, 0));
        return vbox;
    }

    
    private static void showEditDialog(Product product) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit Product");
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.getStyleClass().add("edit-dialog");
    
        Label titleLabel = new Label("Edit Product: " + product.getName());
        titleLabel.getStyleClass().add("title-edit-dialog");
        
        TextField nameField = new TextField(product.getName() != null ? product.getName() : "");
        nameField.setPromptText("Product Name");
        nameField.getStyleClass().add("edit-field");
    
        TextField priceField = new TextField(String.valueOf(product.getPrice()));
        priceField.setPromptText("Price");
        priceField.getStyleClass().add("edit-field");
    
        TextField stockField = new TextField(String.valueOf(product.getStock()));
        stockField.setPromptText("Stock");
        stockField.getStyleClass().add("edit-field");
    
        TextArea descField = new TextArea(product.getDescription() != null ? product.getDescription() : "");
        descField.setPromptText("Description");
        descField.getStyleClass().addAll("edit-field", "edit-text-area");
    
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Make Up", "Skin Care", "Fragrance");
        categoryBox.setValue(product.getCategory() != null ? product.getCategory() : "");
        categoryBox.getStyleClass().add("edit-combo-box");
    
        Button photoButton = new Button("Change Photo");
        photoButton.getStyleClass().add("photo-button");
        photoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                selectedImageFile = file;
            }
        });
    
        Button saveButton = new Button("Save Changes");
        saveButton.getStyleClass().add("edit-button");
        saveButton.setOnAction(e -> {
            boolean hasChanges = false;
    
            String newName = nameField.getText();
            if (newName != null && !newName.equals(product.getName())) {
                product.setName(newName);
                hasChanges = true;
            }
    
            try {
                double newPrice = Double.parseDouble(priceField.getText());
                if (newPrice != product.getPrice()) {
                    product.setPrice(newPrice);
                    hasChanges = true;
                }
            } catch (NumberFormatException ex) {
            }
    
            try {
                int newStock = Integer.parseInt(stockField.getText());
                if (newStock != product.getStock()) {
                    product.setStock(newStock);
                    hasChanges = true;
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
    
            String newDesc = descField.getText();
            String oldDesc = product.getDescription();
            if ((newDesc != null && !newDesc.equals(oldDesc)) || (newDesc == null && oldDesc != null)) {
                product.setDescription(newDesc);
                hasChanges = true;
            }
    
            String newCategory = categoryBox.getValue();
            if (newCategory != null && !newCategory.equals(product.getCategory())) {
                product.setCategory(newCategory);
                hasChanges = true;
            }
    
            if (hasChanges || selectedImageFile != null) {
                updateProductByProduct(product);
            }
            dialog.close();
        });
    
        content.getChildren().addAll(titleLabel, nameField, priceField, stockField, descField, categoryBox, photoButton, saveButton);
        Scene scene = new Scene(content);
        scene.getStylesheets().add("styles.css"); // atau "file:styles.css" jika berada di luar classpath
        dialog.setScene(scene);
        dialog.showAndWait();
    }


    
    private static void updateProductByProduct(Product product) {
        System.out.println("Updating product");
        if (selectedImageFile != null) {
            // Jika ada gambar baru dipilih, gunakan updateProduct
            ProductController.updateProduct(
                product.getId(), 
                product.getName(), 
                product.getDescription(), 
                product.getCategory(), 
                product.getStock(), 
                product.getRate(), 
                product.getPrice(), 
                selectedImageFile,
                product.getTotalRate(), 
                product.getSellerId()
            );
        } else {
            // Jika tidak ada gambar baru, gunakan updateProductWithoutPhoto
            ProductController.updateProductWithoutPhoto(
                product.getId(), 
                product.getName(), 
                product.getDescription(), 
                product.getCategory(), 
                product.getStock(), 
                product.getRate(), 
                product.getPrice(), 
                product.getTotalRate(), 
                product.getSellerId()
            );
        }
        selectedImageFile = null; // Reset setelah digunakan
    }

    private static void showDeleteConfirmationDialog(Product product, TableView<Product> tableView) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Are you sure you want to delete this product?");
        alert.setContentText("Product: " + product.getName());
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = ProductController.deleteProduct(product.getId());
            if (deleted) {
                tableView.getItems().remove(product);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete product.");
                errorAlert.showAndWait();
            }
        }
    }


    private static VBox showHistory(int userId) {
    Label historyLabel = new Label("Purchase History");
    historyLabel.getStyleClass().add("sellerMenuName");

    TableView<History> table = new TableView<>();
    table.setEditable(false);

    TableColumn<History, String> productNameCol = new TableColumn<>("Product Name");
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
    productNameCol.setPrefWidth(200); // Lebar awal untuk kolom "Product Name"

    TableColumn<History, Double> priceCol = new TableColumn<>("Income");
    priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    priceCol.setPrefWidth(120); // Lebar awal untuk kolom "Income"

    TableColumn<History, Integer> quantityCol = new TableColumn<>("Total Purchased");
    quantityCol.setCellValueFactory(new PropertyValueFactory<>("totalProduct"));
    quantityCol.setPrefWidth(120); // Lebar awal untuk kolom "Total Purchased"

    TableColumn<History, String> customerNameCol = new TableColumn<>("Buyer Name");
    customerNameCol.setCellValueFactory(cellData -> {
        try {
            User customer = UserController.getUserById(cellData.getValue().getCustomerId());
            return new SimpleStringProperty(customer.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return new SimpleStringProperty("");
        }
    });
    customerNameCol.setPrefWidth(150); // Lebar awal untuk kolom "Buyer Name"


    TableColumn<History, String> customerEmailCol = new TableColumn<>("Buyer Email");
    customerEmailCol.setCellValueFactory(cellData -> {
        try {
            User customer = UserController.getUserById(cellData.getValue().getCustomerId());
            return new SimpleStringProperty(customer.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            return new SimpleStringProperty("");
        }
    });
    customerEmailCol.setPrefWidth(250);

    TableColumn<History, String> customerAddressCol = new TableColumn<>("Buyer Address");
    customerAddressCol.setCellValueFactory(cellData -> {
        try {
            User customer = UserController.getUserById(cellData.getValue().getCustomerId());
            return new SimpleStringProperty(customer.getAlamat());
        } catch (SQLException e) {
            e.printStackTrace();
            return new SimpleStringProperty("");
        }
    });
    customerAddressCol.setPrefWidth(300);

    TableColumn<History, String> customerPhoneCol = new TableColumn<>("Buyer Phone");
    customerPhoneCol.setCellValueFactory(cellData -> {
        try {
            User customer = UserController.getUserById(cellData.getValue().getCustomerId());
            return new SimpleStringProperty(customer.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
            return new SimpleStringProperty("");
        }
    });
    customerPhoneCol.setPrefWidth(150);

    TableColumn<History, Void> deleteCol = new TableColumn<>("Action");
    deleteCol.setPrefWidth(100);

    Callback<TableColumn<History, Void>, TableCell<History, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<History, Void> call(final TableColumn<History, Void> param) {
            final TableCell<History, Void> cell = new TableCell<>() {
                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction(event -> {
                        History history = getTableView().getItems().get(getIndex());

                        boolean deleted = HistoryController.deleteHistoryById(history.getId());
                        if (deleted) {
                            getTableView().getItems().remove(history);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to delete history entry.");
                            alert.showAndWait();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setAlignment(Pos.CENTER);
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    deleteCol.setCellFactory(cellFactory);

    table.getColumns().addAll(productNameCol, priceCol, quantityCol, customerNameCol, customerEmailCol, customerAddressCol, customerPhoneCol, deleteCol);

    double fixedColumnsWidth = priceCol.getPrefWidth() + quantityCol.getPrefWidth() + customerNameCol.getPrefWidth() + customerEmailCol.getPrefWidth() + customerAddressCol.getPrefWidth() + customerPhoneCol.getPrefWidth() + deleteCol.getPrefWidth();

    productNameCol.prefWidthProperty().bind(table.widthProperty().subtract(fixedColumnsWidth).subtract(2)); // 2 piksel untuk garis tepi
    
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    List<History> purchaseHistory = HistoryController.getAllHistoryBySellerId(userId);
    table.getItems().addAll(purchaseHistory);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(table);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    // scrollPane.setFitToWidth(true); // Mengatur lebar ScrollPane sesuai dengan lebar TableView
    // scrollPane.setFitToHeight(true); // Mengatur tinggi ScrollPane sesuai dengan tinggi TableView

    VBox vbox = new VBox(10, historyLabel, scrollPane);
    vbox.setPadding(new Insets(20));
    return vbox;
}


    



    private void showSellerMode(int userId, int otherUserId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        StackPane stackPane = new StackPane();
        // ObservableList<Node> children = sisiKiriProfileScene.getChildren();
        // StackPane forSellerMode = new StackPane();
        // for (Node node : children) {
        //     if (node instanceof VBox) {
        //         VBox vbox = (VBox) node;
        //         if (vbox.getId() != null && vbox.getId().equals("mainContent")) {
        //         }
        //     }
        // }





        User user = UserController.getUserById(userId);
        ImageView fotoProfil = imageSet.setImages(user.getPhotoFile(), 103, 98);
        ImageView borderPict = imageSet.setImages("/images/product/border.png", 103, 98);
        StackPane fotoPane = new StackPane(fotoProfil, borderPict);
        fotoPane.setAlignment(Pos.CENTER);
        Label MyProfile = new Label("My Profile");
        Label SellerMode = new Label("Seller Mode");
        Label Logout = new Label("Logout");
        Label Chat = new Label("Chat");
        Logout.getStyleClass().add("category");
        MyProfile.getStyleClass().add("category");
        SellerMode.getStyleClass().add("category");
        Chat.getStyleClass().add("category");

        VBox menuProfil = new VBox();
        if (userId == otherUserId) {
            menuProfil.getChildren().addAll(fotoPane, MyProfile, SellerMode, Logout);
        }else{
            menuProfil.getChildren().addAll(fotoPane, Chat);
        }
        menuProfil.setAlignment(Pos.CENTER);
        menuProfil.setSpacing(40);

        Rectangle rectangle = new Rectangle(201, 410);
        rectangle.setStyle("-fx-fill: #ffffff; -fx-border-radius: 7");
        StackPane profileInfoSeller = new StackPane(rectangle, menuProfil);
        profileInfoSeller.setAlignment(Pos.CENTER_LEFT);
        profileInfoSeller.setMinWidth(250);

        // Tambahkan tombol unggah foto
        Button uploadPhotoButton = new Button("Upload Photo Profile");
        uploadPhotoButton.getStyleClass().add("ButtonProfil");
        if (userId == otherUserId) {
            menuProfil.getChildren().add(uploadPhotoButton);
        }

        uploadPhotoButton.setOnAction(e -> {
            File file = photoUploader.upload();
            if (file != null) {
                ImageView newFotoProfil = imageSet.setImages(file, 103, 98);
                fotoPane.getChildren().set(0, newFotoProfil);

                try {
                    UserController.updateUser(userId,
                            user.getName(), user.getUsername(),
                            user.getPassword(), user.getEmail(), user.getAlamat(), user.getPhone(), user.getGender(),
                            file, user.getSellerMode(), user.getTotalNotif(), user.getListChatId());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

        });

        SellerMode.setStyle("-fx-background-color: #DEA0BC;");
        MyProfile.setStyle("-fx-background-color: #ffffff;");
        Logout.setStyle("-fx-background-color: #ffffff;");
        Button askForComplete = new Button();


        MyProfile.setOnMouseClicked(e -> {
            MyProfile.setStyle("-fx-background-color: #DEA0BC;");
            SellerMode.setStyle("-fx-background-color: #ffffff;");
            Logout.setStyle("-fx-background-color: #ffffff;");
            try {
                new ProfileScene(stage).show(userId, userId);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
            
        });

        SellerMode.setOnMouseClicked(e -> {
            SellerMode.setStyle("-fx-background-color: #DEA0BC;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            Logout.setStyle("-fx-background-color: #ffffff;");
            });
        

        Chat.setOnMouseClicked(e->{
            try {
                Conversation conversation = ConversationController.getConversationByParticipantsId(ParticipantFormat.getConsistentIdString(userId, otherUserId));
                if (conversation != null) {
                    new ChatScene(stage).show(userId, otherUserId);
                } else{
                    ConversationController.addConversation(ParticipantFormat.getConsistentIdString(userId, otherUserId), "[]");
                }
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        Button confirLogout = new Button("Klik jika ingin logout");
        Button tidakJadiLogout = new Button("Tidak jadi logout");
        
        confirLogout.getStyleClass().add("ButtonProfil");
        tidakJadiLogout.getStyleClass().add("ButtonProfil");

        confirLogout.setVisible(false);
        tidakJadiLogout.setVisible(false);
        askForComplete.setVisible(false);

        










        Label addProduct = new Label("ADD PRODUCT");
        addProduct.getStyleClass().add("menuIconSeller");
        Label yourProduct = new Label("YOUR PRODUCT");
        yourProduct.getStyleClass().add("menuIconSeller");
        Label history = new Label("HISTORY");
        history.getStyleClass().add("menuIconSeller");
        HBox hBox = new HBox(10, addProduct, yourProduct, history);
        addProduct.setStyle("-fx-background-color: #000000; -fx-text-fill: #DEA0BC;");


        VBox containMenu = new VBox(10, hBox, showAddProduct(userId));
        containMenu.setPadding(new Insets(20, 0, 0, 0));
        HBox all = new HBox(profileInfoSeller, containMenu);


        addProduct.setOnMousePressed(e -> {
            addProduct.setStyle("-fx-background-color: #000000; -fx-text-fill: #DEA0BC;");
            yourProduct.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            history.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            try {
                new ProfileScene(stage).showSellerMode(userId, otherUserId);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
            
        });
        
        yourProduct.setOnMousePressed(e -> {
            addProduct.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            yourProduct.setStyle("-fx-background-color: #000000; -fx-text-fill: #DEA0BC;");
            history.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            try {
                containMenu.getChildren().set(1, showYourProduct(userId));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }  
        });
        
        history.setOnMousePressed(e -> {
            addProduct.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            yourProduct.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
            history.setStyle("-fx-background-color: #000000; -fx-text-fill: #DEA0BC;");
            containMenu.getChildren().set(1, showHistory(userId));  
        });
        



        Logout.setOnMouseClicked(e -> {
            
            Logout.setStyle("-fx-background-color: #DEA0BC;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            SellerMode.setStyle("-fx-background-color: #ffffff;");
            confirLogout.setVisible(true);
            tidakJadiLogout.setVisible(true);
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0); // Set the radius of the shadow
            dropShadow.setOffsetX(0); // Set the horizontal offset of the shadow
            dropShadow.setOffsetY(0); // Set the vertical offset of the shadow
            dropShadow.setColor(Color.color(0.0, 0.0, 0.0, 0.5)); // Set the color of the shadow
            dropShadow.setSpread(3); // Set the spread of the shadow

            VBox logoutConfir = new VBox(confirLogout, tidakJadiLogout);
            logoutConfir.setEffect(dropShadow);
            logoutConfir.setAlignment(Pos.CENTER);
            stackPane.getChildren().addAll(logoutConfir);

        });
        confirLogout.setOnMouseClicked(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();

        });
        tidakJadiLogout.setOnMouseClicked(e -> {
            tidakJadiLogout.setVisible(false);
            confirLogout.setVisible(false);
            try {
                new ProfileScene(stage).showSellerMode(userId, otherUserId);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                    | InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        askForComplete.setOnAction(e->{
            askForComplete.setVisible(false);
        });
        stackPane.getChildren().addAll(all);


        
    
        //MAIN
        VBox navbar = Navbar.getNavbar(stage, userId);
        VBox sellerModeLayout = new VBox(navbar, stackPane);
        Scene scene = new Scene(sellerModeLayout, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }




    private void showConfirmSellerModeDialog(int userId, Stage stage, StackPane sisiKiriProfileScene, int otherUserId) {
        Stage confirmationStage = new Stage();
        confirmationStage.initOwner(stage);
        confirmationStage.setTitle("Confirmation");
    
        Label confirmationLabel = new Label("Are you sure you want to become a seller?");
        confirmationLabel.getStyleClass().addAll("askRating", "title");
        confirmationLabel.setAlignment(Pos.CENTER);
        confirmationLabel.setWrapText(true);
        confirmationLabel.setMaxWidth(300);
        confirmationLabel.setStyle("-fx-font-size: 18px;");
    
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");
        confirmButton.getStyleClass().addAll("askRating", "button", "rate-button");
        cancelButton.getStyleClass().addAll("askRating", "button", "cancel-button");
        confirmButton.setPrefSize(200, 40);
        cancelButton.setPrefSize(200, 40);
    
        HBox buttonBox = new HBox(20, cancelButton, confirmButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox confirmationPane = new VBox(20, confirmationLabel, buttonBox);
        confirmationPane.getStyleClass().add("askRating");
        confirmationPane.setAlignment(Pos.TOP_CENTER);
        confirmationPane.setPadding(new Insets(20));
    
        Scene confirmationScene = new Scene(confirmationPane, 400, 300);
        confirmationScene.getStylesheets().add("styles.css");
        confirmationStage.setScene(confirmationScene);
    
        confirmButton.setOnAction(confirmEvent -> {
            try {
                UserController.updateUserSellerMode(userId, "Seller");
                new ProfileScene(stage).showSellerMode(userId, otherUserId);
                setSellerMode(true);
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
            confirmationStage.close();
        });
    
        cancelButton.setOnAction(cancelEvent -> confirmationStage.close());
    
        confirmationStage.showAndWait();
    }




    public void show(int userId, int otherUserId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        User user = UserController.getUserById(userId);
        ImageView fotoProfil = imageSet.setImages(user.getPhotoFile(), 103, 98);
        ImageView borderPict = imageSet.setImages("/images/product/border.png", 103, 98);
        StackPane fotoPane = new StackPane(fotoProfil, borderPict);
        fotoPane.setAlignment(Pos.CENTER);
        Label MyProfile = new Label("My Profile");
        Label SellerMode = new Label("Seller Mode");
        Label Logout = new Label("Logout");
        Label Chat = new Label("Chat");
        Logout.getStyleClass().add("category");
        MyProfile.getStyleClass().add("category");
        SellerMode.getStyleClass().add("category");
        Chat.getStyleClass().add("category");

        VBox menuProfil = new VBox();
        if (userId == otherUserId) {
            menuProfil.getChildren().addAll(fotoPane, MyProfile, SellerMode, Logout);
        }else{
            menuProfil.getChildren().addAll(fotoPane, Chat);
        }
        menuProfil.setAlignment(Pos.CENTER);
        menuProfil.setSpacing(40);

        Rectangle sisiKiriProfileSceneElm = new Rectangle(201, 410);
        sisiKiriProfileSceneElm.setStyle("-fx-fill: #ffffff; -fx-border-radius: 7");
        StackPane sisiKiriProfileScene = new StackPane(sisiKiriProfileSceneElm, menuProfil);
        sisiKiriProfileScene.setAlignment(Pos.CENTER_LEFT);
        sisiKiriProfileScene.setMinWidth(250);

        // Tambahkan tombol unggah foto
        Button uploadPhotoButton = new Button("Upload Photo Profile");
        uploadPhotoButton.getStyleClass().add("ButtonProfil");
        if (userId == otherUserId) {
            menuProfil.getChildren().add(uploadPhotoButton);
        }

        uploadPhotoButton.setOnAction(e -> {
            File file = photoUploader.upload();
            if (file != null) {
                ImageView newFotoProfil = imageSet.setImages(file, 103, 98);
                fotoPane.getChildren().set(0, newFotoProfil);

                try {
                    UserController.updateUser(userId,
                            user.getName(), user.getUsername(),
                            user.getPassword(), user.getEmail(), user.getAlamat(), user.getPhone(), user.getGender(),
                            file, user.getSellerMode(), user.getTotalNotif(), user.getListChatId());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

        });

        MyProfile.setStyle("-fx-background-color: #DEA0BC;");
        SellerMode.setStyle("-fx-background-color: #ffffff;");
        Logout.setStyle("-fx-background-color: #ffffff;");
        Button askForComplete = new Button();


        MyProfile.setOnMouseClicked(e -> {
            MyProfile.setStyle("-fx-background-color: #DEA0BC;");
            SellerMode.setStyle("-fx-background-color: #ffffff;");
            Logout.setStyle("-fx-background-color: #ffffff;");
            if (sellerMode) {
                try {
                    new ProfileScene(stage).show(userId, otherUserId);
                } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                        | InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        SellerMode.setOnMouseClicked(e -> {
            if (user.getAlamat() != null && user.getPhone() != null) {
                SellerMode.setStyle("-fx-background-color: #DEA0BC;");
                MyProfile.setStyle("-fx-background-color: #ffffff;");
                Logout.setStyle("-fx-background-color: #ffffff;");
                // sellerMode = true;
                if (user.getSellerMode().equals("Seller")) {
                    try {
                        new ProfileScene(stage).showSellerMode(userId, otherUserId);
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                            | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    
                } else{
                    showConfirmSellerModeDialog(userId, stage, sisiKiriProfileScene, otherUserId);
                    try {
                        new ProfileScene(stage).show(userId, otherUserId);
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException
                            | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    setSellerMode(true);
                }
                
            } else {
                MyProfile.setStyle("-fx-background-color: #DEA0BC;");
                SellerMode.setStyle("-fx-background-color: #ffffff;");
                askForComplete.setText("Complete your profile first");
                askForComplete.getStyleClass().add("ButtonProfil");
                askForComplete.setVisible(true);
            }
        });

        

        Chat.setOnMouseClicked(e->{
            try {
                Conversation conversation = ConversationController.getConversationByParticipantsId(ParticipantFormat.getConsistentIdString(userId, otherUserId));
                if (conversation != null) {
                    new ChatScene(stage).show(userId, otherUserId);
                } else{
                    ConversationController.addConversation(ParticipantFormat.getConsistentIdString(userId, otherUserId), "[]");
                }
            } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        Button confirLogout = new Button("Klik jika ingin logout");
        Button tidakJadiLogout = new Button("Tidak jadi logout");
        
        confirLogout.getStyleClass().add("ButtonProfil");
        tidakJadiLogout.getStyleClass().add("ButtonProfil");

        // Informasi Profil
        Label infoProfilLabel = new Label("Profile Information");
        infoProfilLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Estedad-VF'; -fx-font-fill: #000000; -fx-font-size: 17px;"); 
        infoProfilLabel.getStyleClass().add("labelSubProfil");

        Label namaLabel = new Label("Name");
        TextField namaField = new TextField(user.getName());
        namaField.setEditable(false);
        namaLabel.getStyleClass().add("labelIMyProfil");
        namaField.getStyleClass().add("fieldMyProfil");

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField(user.getEmail());
        emailField.setEditable(false);
        emailLabel.getStyleClass().add("labelIMyProfil");
        emailField.getStyleClass().add("fieldMyProfil");

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField(user.getUsername());
        usernameField.setEditable(false);
        usernameLabel.getStyleClass().add("labelIMyProfil");
        usernameField.getStyleClass().add("fieldMyProfil");

        PasswordField passwordField = new PasswordField();
        passwordField.setText("*****");
        Label passwordLabel = new Label("Password");
        passwordField.setEditable(false);
        passwordLabel.getStyleClass().add("labelIMyProfil");
        passwordField.getStyleClass().add("fieldMyProfil");

        CheckBox maleCheckBox = new CheckBox("Male");
        CheckBox femaleCheckBox = new CheckBox("Female");
        maleCheckBox.setDisable(true);
        femaleCheckBox.setDisable(true);
        maleCheckBox.getStyleClass().add("custom-checkbox");
        femaleCheckBox.getStyleClass().add("custom-checkbox");



        Label genderLabel = new Label("Gender");
        genderLabel.getStyleClass().add("labelIMyProfil");
        Label genderValueLabel = new Label();
        

        GridPane infoDataUserGrid = new GridPane();
        infoDataUserGrid.setHgap(10);
        infoDataUserGrid.setVgap(10);
        infoDataUserGrid.add(namaLabel, 0, 0);
        infoDataUserGrid.add(namaField, 1, 0);
        infoDataUserGrid.add(emailLabel, 0, 1);
        infoDataUserGrid.add(emailField, 1, 1);
        infoDataUserGrid.add(usernameLabel, 0, 2);
        infoDataUserGrid.add(usernameField, 1, 2);
        infoDataUserGrid.add(passwordLabel, 0, 3);
        infoDataUserGrid.add(passwordField, 1, 3);
        infoDataUserGrid.add(genderLabel, 0, 4);
        infoDataUserGrid.add(genderValueLabel, 1, 4);
        HBox genderBox = new HBox(10, maleCheckBox, femaleCheckBox);
        infoDataUserGrid.add(genderBox, 1, 4);
        // infoDataUserGrid.add(btnEdit, 10, 0);
        // infoDataUserGrid.add(btnSaveChanges, 10, 0);
        infoDataUserGrid.getStyleClass().add("InformasiData");
        // Hapus dari GridPane infoDataUserGrid
        infoDataUserGrid.getChildren().removeAll(genderLabel, genderValueLabel, genderBox);

        VBox infoBox = new VBox(10, infoProfilLabel, infoDataUserGrid);
        Rectangle persegiAbu = new Rectangle();
        persegiAbu.getStyleClass().add("persegiAbuProfile");
        StackPane inforProfil = new StackPane(persegiAbu, infoBox);

        // Detail Alamat
        Label detailAlamatLabel = new Label("Address Detail");
        detailAlamatLabel.setStyle(
                "-fx-font-weight: bold; -fx-font-family: 'Estedad-VF'; -fx-font-fill: #000000; -fx-font-size: 17px;");
        detailAlamatLabel.getStyleClass().add("labelSubProfil");

        Label phoneLabel = new Label("Phone Number");
        TextField phoneField = new TextField(user.getPhone());
        phoneField.setEditable(false);
        phoneField.setPromptText("62...");
        phoneLabel.getStyleClass().add("labelIMyProfil");
        phoneField.getStyleClass().add("fieldMyProfil");

        Label addressLabel = new Label("Address");
        TextField addressField = new TextField(user.getAlamat());
        addressField.setEditable(false);
        addressField.setPromptText("Jalan, Kelurahan, Kecamatan, Kab/Kota, Provinsi, Kode pos");
        addressField.setPrefColumnCount(30);
        addressLabel.getStyleClass().add("labelIMyProfil");
        addressField.maxHeight(45);
        addressField.getStyleClass().add("fieldMyProfil");

        Button btnEdit = new Button("Edit Profile Information and Address Details");
        if (userId == otherUserId) {
            btnEdit.setVisible(true);
        } else{
            btnEdit.setVisible(false);
        }
        Button btnSaveChanges = new Button("Save Changes");
        btnEdit.getStyleClass().add("ButtonProfil");
        btnSaveChanges.getStyleClass().add("ButtonProfil");
        btnSaveChanges.setVisible(false);

        btnEdit.setOnAction(e -> {
            phoneField.setEditable(true);
            addressField.setEditable(true);
            namaField.setEditable(true);
            passwordField.setEditable(true);
            btnEdit.setVisible(false);
            btnSaveChanges.setVisible(true);
        });

        btnSaveChanges.setOnAction(e -> {
            // Simpan perubahan ke database (implementasi tergantung pada aplikasi)
            btnEdit.setVisible(false);
            btnSaveChanges.setVisible(true);
            String alamat = addressField.getText();
            String phoneNum = phoneField.getText();
            String name = namaField.getText();
            String password = passwordField.getText();
            String passwordToUpdate = user.getPassword();
            if (!password.isEmpty()) {
                if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                    passwordField.setText(null);
                    passwordField.setPromptText("Password minimal 8 karakter terdiri\nhurufbesar, huruf kecil, dan angka");
                    return;
                }
                passwordToUpdate = PasswordHasher.doHashing(password);
            }
            boolean isSuccesfullUpdated;
            try {
                isSuccesfullUpdated = UserController.updateUserMyProfile(userId, name,
                        user.getUsername(), passwordToUpdate,
                        user.getEmail(), alamat, phoneNum, null);
                if (isSuccesfullUpdated) {
                    btnSaveChanges.setVisible(false);
                    btnEdit.setVisible(true);
                    
                    // ProfileScene.showMyProfile(userId, sisiKiriProfileScene);
                    return;

                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        });

        GridPane detailAlamatGrid = new GridPane();
        detailAlamatGrid.setHgap(10);
        detailAlamatGrid.setVgap(10);
        detailAlamatGrid.add(detailAlamatLabel, 0, 0);
        detailAlamatGrid.add(phoneLabel, 0, 1);
        detailAlamatGrid.add(phoneField, 1, 1);
        detailAlamatGrid.add(addressLabel, 0, 2);
        detailAlamatGrid.add(addressField, 1, 2);

        // Mengatur ukuran GridPane
        detailAlamatGrid.setMinWidth(400);
        detailAlamatGrid.setMinHeight(100);

        StackPane btnEditdanSave = new StackPane(btnSaveChanges, btnEdit);
        btnEditdanSave.setAlignment(Pos.CENTER);
        VBox addressBox = new VBox(10, detailAlamatLabel, detailAlamatGrid, btnEditdanSave);

        VBox infoAndAddressBox = new VBox(20, inforProfil, addressBox);

        ScrollPane scrollPane = new ScrollPane(infoAndAddressBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(700);

        HBox keseluruhanScene = new HBox(10, sisiKiriProfileScene, scrollPane);

        // Root layout
        // VBox navbar = Navbar.getNavbar(stage, userId);
        // if (sellerMode) {
            //     navbar = Navbar.getNavbarSeller(stage, userId);
            // } else{
        //     navbar = Navbar.getNavbar(stage, userId);
        // }
        
        
        VBox mainContent = new VBox();
        VBox navbar = new VBox();
        if (userId == otherUserId) {
            navbar = Navbar.getNavbar(stage, userId);
            mainContent.getChildren().addAll(navbar, keseluruhanScene);
        } else{
            navbar = Navbar.getNavbar(stage, otherUserId);
            mainContent.getChildren().addAll(navbar, keseluruhanScene);
        }
        // mainContent.getStyleClass().add("mainContent");

        StackPane rootPane = new StackPane(mainContent);
        Scene scene = new Scene(rootPane, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();

        confirLogout.setVisible(false);
        tidakJadiLogout.setVisible(false);
        askForComplete.setVisible(false);
        rootPane.getChildren().add(askForComplete);

        Logout.setOnMouseClicked(e -> {
            
            Logout.setStyle("-fx-background-color: #DEA0BC;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            MyProfile.setStyle("-fx-background-color: #ffffff;");
            confirLogout.setVisible(true);
            tidakJadiLogout.setVisible(true);
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0); // Set the radius of the shadow
            dropShadow.setOffsetX(0); // Set the horizontal offset of the shadow
            dropShadow.setOffsetY(0); // Set the vertical offset of the shadow
            dropShadow.setColor(Color.color(0.0, 0.0, 0.0, 0.5)); // Set the color of the shadow
            dropShadow.setSpread(3); // Set the spread of the shadow

            VBox logoutConfir = new VBox(confirLogout, tidakJadiLogout);
            logoutConfir.setEffect(dropShadow);
            logoutConfir.setAlignment(Pos.CENTER);
            rootPane.getChildren().add(logoutConfir);

        });
        confirLogout.setOnMouseClicked(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();

        });
        tidakJadiLogout.setOnMouseClicked(e -> {
            tidakJadiLogout.setVisible(false);
            confirLogout.setVisible(false);
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
        });

        askForComplete.setOnAction(e->{
            askForComplete.setVisible(false);
        });

        

    }

    @Override
    public void show() throws InterruptedException {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
