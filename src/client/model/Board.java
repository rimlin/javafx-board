package client.model;

import client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by ilmir on 2016-11-03.
 */
public class Board {
    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    public Board() {
        initData();
    }

    private void initData() {
        messageData.add(new Message("If using different file types, sources prioritizes which file to play, based on order. For example, the player will attempt to play myVideo.m3u8 as a first choice. In the event that a browser cannot play an m3u8, the player is intelligent enough to choose myVideo.mp4 instead. In the event that an mp4 cannot be played, the player will attempt the webm format before producing an error.", "1"));
        messageData.add(new Message("Hans", "2"));
        messageData.add(new Message("Hans", "3"));
    }

    public ObservableList<Message> getMessageData() {
        return messageData;
    }

    public void addMessage(Message message) {
        messageData.add(message);
        Main.getInstance().clientService.sendMessage(message);
    }
}
