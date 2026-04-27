/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import mediawidget.util.AppLog;
import mediawidget.viewmodel.MediaViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlMVM implements Initializable{

    private final MediaViewModel vm;

    public ControlMVM(MediaViewModel vm) {
        this.vm = vm;
    }

    @FXML
    private Button volumeDown;
    
    @FXML
    private Button previous;
    
    @FXML
    private Button togglePlayback;
    
    @FXML
    private Button next;
    
    @FXML
    private Button volumeUp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bind(togglePlayback, "togglePlayback", vm::togglePlayback);
        bind(next, "next", vm::next);
        bind(previous, "previous", vm::previous);
        bind(volumeUp, "volumeUp", vm::volumeUp);
        bind(volumeDown, "volumeDown", vm::volumeDown);
    }

    private void bind(Button button, String actionName, Runnable action) {
        button.setOnAction(event -> {
            AppLog.info("Button clicked: " + actionName);
            try {
                action.run();
                AppLog.info("Action completed: " + actionName);
            } catch (Throwable t) {
                AppLog.error("Action failed: " + actionName, t);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MediaWidget Error");
                alert.setHeaderText("Action failed: " + actionName);
                alert.setContentText(t.getClass().getSimpleName() + ": " + t.getMessage());
                alert.show();
            }
        });
    }
}
