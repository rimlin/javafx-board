package client.controller;

import client.Main;
import client.model.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by ilmir on 2016-11-14.
 */
public class MessageView {
    Message messageModel;

    @FXML
    private Label text;

    @FXML
    private Label date;

    @FXML
    private Label author;

    public MessageView(Message message) {
        messageModel = message;
    }

    public void initialize() {
        text.setWrapText(true);
        text.setText(messageModel.getText());
        date.setText(messageModel.getDateFormat());
        author.setText(messageModel.getAuthor());
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        Main.getInstance().showBoardPage();
    }

    @FXML
    protected void handleEdit(ActionEvent event) {
        Main.getInstance().showMessageForm("edit", messageModel);
    }
}
