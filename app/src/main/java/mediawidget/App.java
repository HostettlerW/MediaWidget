/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mediawidget.model.calls.DispatchMSC;
import mediawidget.model.calls.MediaSystemCaller;
import mediawidget.util.AppLog;
import mediawidget.view.ControlMVM;
import mediawidget.viewmodel.ImplMVM;
import mediawidget.viewmodel.MediaViewModel;

public class App extends Application {

    private final String version = "1.1.0";

    @Override
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
            AppLog.error("Uncaught exception on thread " + thread.getName(), throwable)
        );
        AppLog.info("Starting MediaWidget v" + version + " on " + System.getProperty("os.name"));
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        // Dispatch a MediaSystemCaller
        MediaSystemCaller msc = DispatchMSC.create();
        AppLog.info("MediaSystemCaller implementation: " + msc.getClass().getSimpleName());

        // Create the ViewModel
        MediaViewModel viewModel = new ImplMVM(msc);
        
        // Create the controller with the ViewModel
        ControlMVM controller = new ControlMVM(viewModel);
        
        // Load the FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mediaControls.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        
        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        stage.setTitle("MediaWidget");
        stage.setScene(scene);
        stage.show();
        AppLog.info("Primary stage shown");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
