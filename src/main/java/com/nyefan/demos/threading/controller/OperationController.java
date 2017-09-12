package com.nyefan.demos.threading.controller;

import com.nyefan.demos.threading.model.OperationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by Nyefan on 9/11/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class OperationController {

    @FXML
    private TextArea sendTextArea;
    @FXML
    private Button sendTextButton;
    @FXML
    private Label sendTextLabel;

    private OperationModel operationModel;

    public OperationController() {

    }

    @FXML
    public void initialize() {
    }

    public OperationController setOperationModel(OperationModel operationModel) {
        this.operationModel = operationModel;
        sendTextButton.setOnMouseClicked((event) -> this.operationModel.setMessage(sendTextArea.getText()));
        return this;
    }
}
