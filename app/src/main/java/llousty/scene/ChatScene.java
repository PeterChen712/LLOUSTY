package llousty.scene;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import llousty.App;
import llousty.Models.Chat;
import llousty.Models.Comment;
import llousty.Models.Conversation;
import llousty.Models.Message;
import llousty.Models.User;
import llousty.Utils.ParticipantFormat;
import llousty.Utils.StringListConverter;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.ChatController;
import llousty.controller.CommentsController;
import llousty.controller.ConversationController;
import llousty.controller.MessageController;
import llousty.controller.UserController;

public class ChatScene {
    private Stage stage;

    public ChatScene(Stage stage) {
        this.stage = stage;
    }

    private static Conversation getFromDB(int targerUserId, int userId){
        Conversation conversation = ConversationController.getConversationByParticipantsId(ParticipantFormat.getConsistentIdString(targerUserId, userId));
        return conversation;
    }

    
    public void show(int targerUserId, int userId) throws SQLException, FileNotFoundException{
        User mySelf = UserController.getUserById(userId);
        User target = UserController.getUserById(targerUserId);
        Conversation conversation = getFromDB(targerUserId, userId);
        List<Integer> listMessageGet = StringListConverter.stringToListInt(conversation.getMessageIdList());
        List<Integer> listMessageId = listMessageGet;


        // List<Chat> chats = ChatController.getAllChatByTargetId(targerUserId);


        ImageView profileLogo = imageSet.setImages(target.getPhotoFile(), 40, 40);
        ImageView border = imageSet.setImages("/images/product/border.png", 40, 40);
        StackPane imageCombine = new StackPane(profileLogo, border);
        imageCombine.setAlignment(Pos.CENTER_LEFT);
        Button profileMenu = new Button();
        profileMenu.getStyleClass().add("profileMenuChat");
        profileMenu.setGraphic(imageCombine);

        profileMenu.setOnAction(e->{
            //ke profile scene
            System.out.println("gg bang");
        });
        System.out.println("ke sini");


        Label profileName = new Label(target.getName());
        profileName.getStyleClass().add("productNameCart");
        Label profileTarget = new Label(target.getUsername());
        profileTarget.getStyleClass().add("sellerName");

        VBox profileInfo = new VBox(profileName, profileTarget);
        profileInfo.setSpacing(10);

        HBox profileBar = new HBox(profileMenu, profileInfo);


        //SET CHAT
        Label labelTitleChats = new Label("Chat");
        labelTitleChats.getStyleClass().add("labelTitleComments");
        labelTitleChats.setPadding(new Insets(10, 0, 0, 0));

        VBox vBoxBookChats = new VBox();
        vBoxBookChats.setSpacing(7);

        if (listMessageId.size() > 0) {
            for (int messageId : listMessageId) {
                System.out.println(listMessageId.size());
                Message message = MessageController.getMessageByMessageId(messageId);

                Label textTyped = new Label();
                textTyped.setText(message.getText());
                textTyped.setWrapText(true); 
                textTyped.setTextAlignment(TextAlignment.RIGHT);
                HBox textTypedHBox = new HBox(textTyped);
                if (message.getTargetId() == userId) {// 12 11 ke kiri
                    textTypedHBox.setAlignment(Pos.CENTER_LEFT);
                    textTyped.getStyleClass().add("textTypedTarget");
                    
                }
                else if (message.getSenderId() == userId) {// 11 11 ke kanan
                    textTypedHBox.setAlignment(Pos.CENTER_RIGHT);
                    textTyped.getStyleClass().add("textTyped");
                }

                vBoxBookChats.getChildren().addAll(textTypedHBox);

                // // User chatUser = UserController.getUserById(chat.getUserId());
                // Label labelName = new Label(target.getName());

                // Label labelText = new Label(chat.getText());
                // VBox vBoxChat = new VBox(labelName, labelText);
                // vBoxBookChats.getChildren().add(vBoxChat);
            }
        } else {
            Label labelNoChat = new Label("No Chat");
            vBoxBookChats.getChildren().add(labelNoChat);
        }

        Label labelPostChat = new Label("Write a chat");
        TextField textFieldChat = new TextField();
        textFieldChat.setPromptText("Write your messeage");
        textFieldChat.setPrefHeight(50);
        Button buttonPostChat = new Button("Add");
        VBox vBoxPostChat = new VBox(labelPostChat, textFieldChat, buttonPostChat);
        vBoxPostChat.setSpacing(5);

        VBox vBoxChats = new VBox(labelTitleChats, vBoxBookChats, vBoxPostChat);
        vBoxChats.setSpacing(10);

        VBox vBoxMainContent = new VBox(vBoxChats);
        vBoxMainContent.setPadding(new Insets(10));
        vBoxMainContent.setSpacing(15);
        vBoxMainContent.setPrefWidth(620);

        buttonPostChat.setOnAction(e->{
            String text = textFieldChat.getText();
            if (text != null && !text.isEmpty()) {
                // Add comment
                if (MessageController.addMessage(userId, targerUserId, text)) {
                    // listMessageId.add(MessageController.getMessageId(userId, targerUserId, text));
                    listMessageId.add(MessageController.getLatestMessageId());
                    // System.out.println(StringListConverter.listIntToString(listMessageId));
                    System.out.println(MessageController.getLatestMessageId());
                    // System.out.println(MessageController.getMessageId(userId, targerUserId, text));
                    
                    
                    try {
                        int conversationIdNew = conversation.getId();
                        String conversationParticipantsIdNew = conversation.getParticipantsId();
                        if (ConversationController.addConversation(conversationParticipantsIdNew, text)) {
                            System.out.println("Masuk");
                            if (ConversationController.deleteConversationById(conversationIdNew)) {
                                new ChatScene(stage).show(targerUserId, userId);
                            }
                        }
                    } catch (SQLException | FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    





                }
            }
        });


        //MAIN 


        Navbar navbar = new Navbar();
        VBox chatRoot = new VBox(navbar.getNavbar(stage, userId), profileBar, vBoxMainContent);


        Scene scene = new Scene(chatRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }
}