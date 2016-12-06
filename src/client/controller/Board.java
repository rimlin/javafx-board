package client.controller;

import client.Main;
import client.model.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by ilmir on 2016-11-03.
 */
public class Board {
    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    public Board() {
    }

    public void uploadMessages(Message[] list) {
        messageData.addAll(list);

        for (int i = 0; i < list.length; i++) {
            list[i].setupAuthor();
        }
    }

    public Integer getAmount() {
        return messageData.size();
    }

    public ObservableList<Message> getMessageData() {
        return messageData;
    }

    public void addMessage(Message message) {
        messageData.add(message);
        Main.getInstance().clientService.sendMessage(message);
    }
}
