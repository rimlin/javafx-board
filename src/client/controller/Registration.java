package client.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by ilmir on 2016-11-29.
 */
public class Registration {
    @FXML
    private Text error;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        if (!loginField.getText().equals("") &&
                !passwordField.getText().equals(("")) &&
                !nameField.getText().equals("") &&
                !surnameField.getText().equals("")) {
            this.createUser();
        } else {
            error.setText("Введите все поля!");
        }
    }

    private void createUser() {
        Main.getInstance().usersController.regUser(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText());
        Main.getInstance().showBoardPage();
    }
}
