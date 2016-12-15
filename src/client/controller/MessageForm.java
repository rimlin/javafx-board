package client.controller;

import client.Main;
import client.model.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by ilmir on 2016-11-14.
 */
public class MessageForm {
    Message messageModel;
    String formType;

    @FXML
    private VBox form;

    @FXML
    private Button formButton;

    @FXML
    private Text title;

    @FXML
    private TextArea text;

    @FXML
    private Text error;

    public MessageForm(String type) {
        formType = type;
    }

    public MessageForm(String type, Message message) {
        messageModel = message;
        formType = type;
    }

    public void initialize() {
        if (formType == "edit") {
            title.setText("Редактирование");
            formButton.setText("Сохранить");
            text.setText(messageModel.getText());
        } else {
            if (Main.getInstance().boardController.getAmount() >= Main.getInstance().boardController.getOptions().getMaxAmount()) {
                form.setVisible(false);
                error.setText("Превышен лимит на максимальное количество сообщений.");
            } else {
                title.setText("Создание сообщения");
                formButton.setText("Создать");
            }
        }
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        Main.getInstance().showBoardPage();
    }

    @FXML
    protected void handleSubmitForm(ActionEvent event) {
        if (formType == "edit") {
            Main.getInstance().boardController.updateMessage(messageModel.getId(), text.getText());
            Main.getInstance().showBoardPage();
        } else {
            this.createMessage();
        }
    }

    private void createMessage() {
        messageModel = new Message(-1, text.getText(), Main.getInstance().usersController.getCurrentUser().getId(), "-1");
        messageModel.setupAuthor();

        Main.getInstance().boardController.addMessage(messageModel);

        Main.getInstance().showBoardPage();
    }
}
