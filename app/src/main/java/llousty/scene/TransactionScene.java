package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Transaction;
import llousty.Utils.priceFormatter;
import llousty.components.Navbar;
import llousty.controller.TransactionController;

public class TransactionScene {
    private Stage stage;

    public TransactionScene(Stage stage) {
        this.stage = stage;
    }

    public void show(int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        List<Transaction> listTransactions = TransactionController.getAllTransactionByUserId(userId);
        ObservableList<Transaction> observableData = FXCollections.observableArrayList(listTransactions);
        TableView<Transaction> table = new TableView<>();

        TableColumn<Transaction, Integer> firstCol = new TableColumn<>("Date");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("Date"));

        TableColumn<Transaction, Double> secondCol = new TableColumn<>("Total Cost");
        secondCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        secondCol.setCellFactory(column -> new TableCell<Transaction, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("Rp" + priceFormatter.formatCurrency(item));
                }
            }
        });

        TableColumn<Transaction, String> thirdCol = new TableColumn<>("Payment Method");
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("payment"));

        TableColumn<Transaction, String> fourthCol = new TableColumn<>("Product");
        fourthCol.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Transaction, String> fifthtCol = new TableColumn<>("Seller");
        fifthtCol.setCellValueFactory(new PropertyValueFactory<>("seller"));

        TableColumn<Transaction, String> sixthCol = new TableColumn<>("Status");
        sixthCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Transaction, Void> seventhCol = new TableColumn<>("Delete");
        // Button deleteButton = new Button();
        // deleteButton.setGraphic(imageSet.setImages("/images/transaction/delete.png", 20, 20));
        // deleteButton.getStyleClass().add("deleteButton");
        seventhCol.setCellFactory(param -> {
            return new TableCell<Transaction, Void>() {
                private final Button deleteButton = new Button("Delete");
                {
                    deleteButton.setOnAction(event -> {
                        int transactionId = getTableView().getItems().get(getIndex()).getId();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure to delete this transaction ?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            // Jika pengguna mengonfirmasi penghapusan
                            if (TransactionController.deleteTransaction(transactionId)) {
                                try {
                                    new TransactionScene(stage).show(userId);
                                } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Failed");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Error on database, please try again");
                                errorAlert.showAndWait();
                            }
                        }
                    });
                }
                

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });
        


        firstCol.setPrefWidth(150); // Lebar kolom untuk "Date"
        secondCol.setPrefWidth(100); // Lebar kolom untuk "Total Cost"
        thirdCol.setPrefWidth(150); // Lebar kolom untuk "Payment Method"
        fourthCol.setPrefWidth(200); // Lebar kolom untuk "Product"
        fifthtCol.setPrefWidth(150); // Lebar kolom untuk "Seller"
        sixthCol.setPrefWidth(100); // Lebar kolom untuk "Status"
        seventhCol.setPrefWidth(100);
        


        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthtCol, sixthCol, seventhCol);

        table.setItems(observableData);






        //Main
        Navbar navbar = new Navbar();
        VBox transactionRoot = new VBox(Navbar.getNavbar(stage, userId), table);


        Scene scene = new Scene(transactionRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }




}
