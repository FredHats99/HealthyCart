package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    public Label finalScoreLabel;
    @FXML
    public Button showResultsButton;
    @FXML
    public Label nutriscoreLabel;

    @FXML
    public void showFinalScore(ActionEvent actionEvent) {
        showResultsButton.setVisible(false);
        finalScoreLabel.setText("FS is "+String.valueOf(Main.finalScoreAtRun)+ "/100");
        nutriscoreLabel.setText("NS is "+String.valueOf(Main.nutriscoreAtRun));
        //TODO check for better solution
    }
}