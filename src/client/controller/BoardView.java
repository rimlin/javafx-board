package client.controller;

import client.Main;
import client.model.Message;
import client.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by ilmir on 2016-11-14.
 */
public class BoardView {
    @FXML
    private TableView<Message> boardTable;

    @FXML
    private TableColumn<Message, String> textColumn;

    @FXML
    private TableColumn<Message, String> authorColumn;

    @FXML
    private TableColumn<Message, String> dateColumn;


    @FXML
    private void initialize() {
        // устанавливаем тип и значение которое должно хранится в колонке
        textColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("text"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("author"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("formatDate"));

        // заполняем таблицу данными
        ObservableList<Message> messageData = Main.getInstance().boardController.getMessageData();
        boardTable.setItems(messageData);

        boardTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMessageDetails(newValue));
    }

    private void showMessageDetails(Message message) {
        if (message != null) {
            Main.getInstance().showMessageView(message);
        }
    }

    public void refreshTable() {
        boardTable.getColumns().get(0).setVisible(false);
        boardTable.getColumns().get(0).setVisible(true);
    }

    @FXML
    protected void handleCreateMessage(ActionEvent event) {
        Main.getInstance().showMessageForm("create");
    }
}
