/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        togglePlayback.setOnAction(e -> vm.togglePlayback());
        next.setOnAction(e -> vm.next());
        previous.setOnAction(e -> vm.previous());
        volumeUp.setOnAction(e -> vm.volumeUp());
        volumeDown.setOnAction(e -> vm.volumeDown());
    }
}
