package client.controller;

import client.Main;
import client.model.Message;
import client.model.Options;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

/**
 * Created by ilmir on 2016-11-03.
 */
public class Board {
    private ObservableList<Message> messageData = FXCollections.observableArrayList();
    private Options options;

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

    public void updateMessage(Integer id, String text) {
        Optional<Message> findMessage = messageData
                                .stream()
                                .filter(msg -> msg.getId() == id)
                                .findFirst();

        if (findMessage.isPresent()) {
            findMessage.get().setText(text);
            Main.getInstance().boardViewController.refreshTable();
        }
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

    public void updateOptions(Options options) {
        setOptions(options);

        Main.getInstance().clientService.sendOptions(options);
    }
}
