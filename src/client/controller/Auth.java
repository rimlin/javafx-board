package client.controller;

import client.Main;
import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Optional;

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
        Optional checkUser = Main.getInstance().usersController.getUserByLoginPassword(loginField.getText(), passwordField.getText());

        if (checkUser.isPresent() && checkUser.get() instanceof User) {
            Main.getInstance().showBoardPage();
        } else {
            error.setText("Неправильное имя пользователя или пароль!");
        }
    }

    @FXML
    protected void handleReg(ActionEvent event) {
        Main.getInstance().showRegPage();
    }
}
