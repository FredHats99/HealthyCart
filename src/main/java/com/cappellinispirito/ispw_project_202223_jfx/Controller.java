package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    public Label finalScoreLabel;
    @FXML
    public Button showFinalScoreButton;

    @FXML
    public void showFinalScore(ActionEvent actionEvent) {
        showFinalScoreButton.setVisible(false);
        finalScoreLabel.setText(String.valueOf(Main.additiveAtRun)); //TODO check for better solution
    }
}