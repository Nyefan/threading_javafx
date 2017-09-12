package com.nyefan.demos.threading.controller;
/**
 * Created by Nyefan on 9/11/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */

import com.nyefan.demos.threading.model.DataViewerModel;
import com.nyefan.demos.threading.model.MKSetupModel;
import com.nyefan.demos.threading.model.OperationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

    private Stage dataViewerStage;
    private Stage mkSetupStage;
    private Stage operationStage;

    @FXML
    private Button dataViewerButton;
    @FXML
    private Button mkSetupButton;
    @FXML
    private Button operationButton;

    public void initialize() {

    }

    public MainController setDataViewerStage(Stage dataViewerStage) {
        this.dataViewerStage = dataViewerStage;
        dataViewerButton.setOnMouseClicked((event) -> dataViewerStage.show());
        return this;
    }

    public MainController setMKSetupStage(Stage mkSetupStage) {
        this.mkSetupStage = mkSetupStage;
        mkSetupButton.setOnMouseClicked((event) -> mkSetupStage.show());
        return this;
    }

    public MainController setOperationStage(Stage operationStage) {
        this.operationStage = operationStage;
        operationButton.setOnMouseClicked((event) -> operationStage.show());
        return this;
    }

}
