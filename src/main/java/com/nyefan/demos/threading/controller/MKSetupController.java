package com.nyefan.demos.threading.controller;/**
 * Created by Nyefan on 9/11/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */

import com.nyefan.demos.threading.model.MKSetupModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class MKSetupController {

    @FXML
    private Label currentTimeLabel;
    @FXML
    private TextField currentTimeHourField;
    @FXML
    private Label currentTimeColon;
    @FXML
    private TextField currentTimeMinuteField;

    @FXML
    private Label newTimeLabel;
    @FXML
    private TextField newTimeHourField;
    @FXML
    private Label newTimeColon;
    @FXML
    private TextField newTimeMinuteField;
    @FXML
    private Button newTimeSubmitButton;

    @FXML
    private Label incomingMessageLabel;
    @FXML
    private TextArea incomingMessageText;


    private MKSetupModel mkSetupModel;

    public MKSetupController() {

    }

    @FXML
    public void initialize() {

    }

    public MKSetupController setMKModel(MKSetupModel mkModel) {
        this.mkSetupModel = mkModel;

        //inputs
        newTimeSubmitButton.setOnMouseClicked((event) ->
                mkSetupModel.setReferenceTime(
                        Integer.valueOf(newTimeHourField.getCharacters().toString()),
                        Integer.valueOf(newTimeMinuteField.getCharacters().toString())
                ));
        //outputs
        incomingMessageText.textProperty().bindBidirectional(this.mkSetupModel.getMessageProperty());
        StringConverter<Number> converter = new NumberStringConverter();
        currentTimeHourField.textProperty().bindBidirectional(this.mkSetupModel.getHourProperty(), converter);
        currentTimeMinuteField.textProperty().bindBidirectional(this.mkSetupModel.getMinuteProperty(), converter);
        return this;
    }
}
