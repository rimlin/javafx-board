package client.controller;

import client.Main;
import client.model.Options;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by ilmir on 2016-12-15.
 */
public class OptionsView {
    @FXML
    private Text error;

    @FXML
    private TextField numMessage;

    public void initialize() {
        numMessage.setText(Main.getInstance().boardController.getOptions().getMaxAmount().toString());
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        if (!numMessage.getText().equals("")) {
            this.updateSetting();
        }
    }

    private void updateSetting() {
        Options options = new Options(Integer.parseInt(numMessage.getText()));
        Main.getInstance().boardController.updateOptions(options);
        Main.getInstance().showBoardPage();
    }
}
