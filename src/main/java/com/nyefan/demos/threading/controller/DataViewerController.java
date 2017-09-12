package com.nyefan.demos.threading.controller;

import com.nyefan.demos.threading.model.DataViewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * Created by Nyefan on 9/11/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class DataViewerController {

    @FXML
    private Label receivedTimeLabel;
    @FXML
    private Label receivedTimeHourLabel;
    @FXML
    private TextField receivedTimeHourField;
    @FXML
    private Label receivedTimeMinuteLabel;
    @FXML
    private TextField receivedTimeMinuteField;

    @FXML
    private Label updateIntervalLabel;
    @FXML
    private TextField updateIntervalField;
    @FXML
    private Button updateIntervalButton;

    private DataViewerModel dataViewerModel;

    public DataViewerController() {

    }

    @FXML
    private void initialize() {
        //inputs
        updateIntervalButton.setOnMouseClicked((event) ->
                dataViewerModel.setUpdateInterval(
                        Double.valueOf(updateIntervalField.getCharacters().toString())));
        //outputs

    }

    public DataViewerController setDataViewerModel(DataViewerModel dataViewerModel) {
        if (receivedTimeHourField.textProperty().isBound()) {
            receivedTimeHourField.textProperty().unbindBidirectional(this.dataViewerModel.getHourProperty());
        }
        if (receivedTimeMinuteField.textProperty().isBound()) {
            receivedTimeMinuteField.textProperty().unbindBidirectional(this.dataViewerModel.getMinuteProperty());
        }

        this.dataViewerModel = dataViewerModel;

        StringConverter<Number> converter = new NumberStringConverter();
        receivedTimeHourField.textProperty().bindBidirectional(this.dataViewerModel.getHourProperty(), converter);
        receivedTimeMinuteField.textProperty().bindBidirectional(this.dataViewerModel.getMinuteProperty(), converter);

        return this;
    }
}
