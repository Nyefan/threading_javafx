package com.nyefan.demos.threading;

import com.nyefan.demos.threading.controller.DataViewerController;
import com.nyefan.demos.threading.controller.MKSetupController;
import com.nyefan.demos.threading.controller.MainController;
import com.nyefan.demos.threading.controller.OperationController;
import com.nyefan.demos.threading.model.DataViewerModel;
import com.nyefan.demos.threading.model.MKSetupModel;
import com.nyefan.demos.threading.model.OperationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Nyefan on 9/12/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class MainApp extends Application {

    private Stage mainStage;
    private DataViewerModel dataViewerModel;
    private MKSetupModel mkSetupModel;
    private OperationModel operationModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;
        initModels();
        initLayout();
        mainStage.setOnCloseRequest((event) -> System.exit(0));
        mainStage.show();
    }

    private void initModels() {
        mkSetupModel = new MKSetupModel();
        operationModel = new OperationModel(mkSetupModel);
        dataViewerModel = new DataViewerModel(mkSetupModel);
    }

    private void initLayout() {
        try {

            FXMLLoader dataViewerLoader = getLoader("DataViewerView.fxml");
            Pane dataViewerView = dataViewerLoader.load();
            ((DataViewerController) dataViewerLoader.getController())
                    .setDataViewerModel(dataViewerModel);
            Stage dataViewerStage = createStage(dataViewerView);
            dataViewerStage.setTitle("Data Viewer Page");

            FXMLLoader mkSetupLoader = getLoader("MKSetupView.fxml");
            Pane mkSetupView = mkSetupLoader.load();
            ((MKSetupController) mkSetupLoader.getController())
                    .setMKModel(mkSetupModel);
            Stage mkSetupStage = createStage(mkSetupView);
            mkSetupStage.setTitle("MK Setup Page");

            FXMLLoader operationLoader = getLoader("OperationView.fxml");
            Pane operationView = operationLoader.load();
            ((OperationController) operationLoader.getController())
                    .setOperationModel(operationModel);
            Stage operationStage = createStage(operationView);
            operationStage.setTitle("Operation Page");

            FXMLLoader mainLoader = getLoader("MainView.fxml");
            Pane mainView = mainLoader.load();
            ((MainController) mainLoader.getController())
                    .setDataViewerStage(dataViewerStage)
                    .setMKSetupStage(mkSetupStage)
                    .setOperationStage(operationStage);
            mainStage.setScene(new Scene(mainView));
            mainStage.setTitle("Home Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader getLoader(String path) throws MalformedURLException {
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(new File(path).toURI().toURL());
        loader.setLocation(getClass().getClassLoader().getResource(path));
        return loader;
    }

    private Stage createStage(Pane pane) {
        Stage stage = new Stage();
        stage.initOwner(mainStage);
        stage.setScene(new Scene(pane));
        return stage;
    }
}
