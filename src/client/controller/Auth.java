package client.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by ilmir on 2016-11-14.
 */
public class Auth {
    @FXML
    private Text error;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Main.getInstance().showBoardPage();
        /*
        if (loginField.getText() != "admin" && passwordField.getText() != "admin") {
            error.setText("Неправильное имя пользователя или пароль!");
        } else {
            Main.getInstance().showBoardPage();
        }*/
    }
}
