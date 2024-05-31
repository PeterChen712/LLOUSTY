package llousty.scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import llousty.App;
import llousty.Abstract.ShowScene;
import llousty.Models.Conversation;
import llousty.Models.Message;
import llousty.Models.User;
import llousty.Utils.ParticipantFormat;
import llousty.Utils.StringListConverter;
import llousty.Utils.imageSet;
import llousty.components.Navbar;
import llousty.controller.ConversationController;
import llousty.controller.MessageController;
import llousty.controller.UserController;

public class ChatScene implements ShowScene{
    private Stage stage;

    public ChatScene(Stage stage) {
        this.stage = stage;
    }

    private static Conversation getFromDB(int targerUserId, int userId){
        Conversation conversation = ConversationController.getConversationByParticipantsId(ParticipantFormat.getConsistentIdString(targerUserId, userId));
        return conversation;
    }

    
    public void show(int targerUserId, int userId) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
        User mySelf = UserController.getUserById(userId);
        User target = UserController.getUserById(targerUserId);
        Conversation conversation = getFromDB(targerUserId, userId);
        List<Integer> cek = StringListConverter.stringToListInt(conversation.getMessageIdList()); // output id antara chat
        List<Integer> cek2 = StringListConverter.stringToListInt(mySelf.getListChatId()); // output siapa saja yg pernah dichat

        boolean add = true;
        outerloop:
        for (Integer integer : cek) {
            if (integer == targerUserId) {
                for (Integer integer2 : cek2) {
                    if (integer == integer2) {
                        add = false;
                        break outerloop;
                    }
                }
            }
        }
        if (add) {
        boolean isSuccesfullUpdated;
            try {
                cek2.add(targerUserId);
                String listChatId = StringListConverter.listIntToString(cek2);
                // isSuccesfullUpdated = UserController.updateUser(userId, mySelf.getName(), mySelf.getUsername(), mySelf.getPassword(), 
                //         mySelf.getEmail(), mySelf.getAlamat(), mySelf.getPhone(), mySelf.getGender(), 
                //         mySelf.getPhotoFile(), mySelf.getSellerMode(), mySelf.getTotalNotif(), listChatId);
                isSuccesfullUpdated = UserController.updateUserListChatId(userId, listChatId);
                if (isSuccesfullUpdated) {
                    new ChatScene(stage).show();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        
        
        List<Integer> listMessageId = StringListConverter.stringToListInt(conversation.getMessageIdList());

        // List<Integer> listMessageId = ;

        
        System.out.println("List message id  : " + String.valueOf(StringListConverter.stringToListInt(conversation.getMessageIdList())));


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
                textTypedHBox.setPrefWidth(700);
                if (message.getTargetId() == userId) {// 12 11 ke kiri
                    textTypedHBox.setAlignment(Pos.CENTER_LEFT);
                    textTyped.getStyleClass().add("textTypedTarget");
                    textTypedHBox.setPadding(new Insets(0, 0, 0, 20));
                    
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

        ScrollPane scrollPane = new ScrollPane(vBoxBookChats);

        VBox vBoxChats = new VBox(labelTitleChats, scrollPane, vBoxPostChat);
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
                    // System.out.println(MessageController.getLatestMessageId());
                    // System.out.println(MessageController.getMessageId(userId, targerUserId, text));
                    
                    int conversationIdNew = conversation.getId();
                    String conversationParticipantsIdNew = conversation.getParticipantsId();
                    boolean isSuccesfullUpdated;
                    
                    try {
                        isSuccesfullUpdated = ConversationController.updateConversation(conversationIdNew, conversationParticipantsIdNew, String.valueOf(listMessageId));
                        System.out.println("isSuccesfullUpdated: "+isSuccesfullUpdated);
                        if (isSuccesfullUpdated) {
                            new ChatScene(stage).show(targerUserId, userId);
                            return;
                        }
                    } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    





                }
            }
        });


        //MAIN 


        VBox chatRoot = new VBox(Navbar.getNavbar(stage, userId), profileBar, vBoxMainContent);


        Scene scene = new Scene(chatRoot, App.getWidth(), App.getHeight());
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
