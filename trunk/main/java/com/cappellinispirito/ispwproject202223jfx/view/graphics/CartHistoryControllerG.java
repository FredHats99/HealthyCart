package com.cappellinispirito.ispwproject202223jfx.view.graphics;
import com.cappellinispirito.ispwproject202223jfx.view.ConsultCartHistoryCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartHistoryControllerG implements Initializable {
    @FXML
    private Label historyCartLabel1;
    @FXML
    private Label historyCartScoreLabel1;
    @FXML
    private Label historyCartLabel2;
    @FXML
    private Label historyCartScoreLabel2;
    @FXML
    private Label historyCartLabel3;
    @FXML
    private Label historyCartScoreLabel3;
    @FXML
    private Label historyCartLabel4;
    @FXML
    private Label historyCartScoreLabel4;
    @FXML
    private Label historyCartLabel5;
    @FXML
    private Label historyCartScoreLabel5;
    @FXML
    private Label historyCartLabel6;
    @FXML
    private Label historyCartScoreLabel6;
    @FXML
    private Label historyCartLabel7;
    @FXML
    private Label historyCartScoreLabel7;
    @FXML
    private StackPane root;
    @FXML
    private Label userNameLabel;
    @FXML
    private Rectangle historyRectangle1;
    @FXML
    private Rectangle historyRectangle2;
    @FXML
    private Rectangle historyRectangle3;
    @FXML
    private Rectangle historyRectangle4;
    @FXML
    private Rectangle historyRectangle5;
    @FXML
    private Rectangle historyRectangle6;
    @FXML
    private Rectangle historyRectangle7;
    @FXML
    private Button deleteButton;
    private final List<Rectangle> historyRectangles = new ArrayList<>();

    private ConsultCartHistoryCustomerView view;
    private final List<Label> historyCartLabels = new ArrayList<>();
    private final List<Label> historyCartScoreLabels = new ArrayList<>();

    private final List<String> cartDates = new ArrayList<>();
    private final List<Integer> cartScores = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        String username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userNameLabel.setText(username);
        }
        view = new ConsultCartHistoryCustomerView();

        view.getCartHistory(username);

        historyCartLabels.add(historyCartLabel1);
        historyCartLabels.add(historyCartLabel2);
        historyCartLabels.add(historyCartLabel3);
        historyCartLabels.add(historyCartLabel4);
        historyCartLabels.add(historyCartLabel5);
        historyCartLabels.add(historyCartLabel6);
        historyCartLabels.add(historyCartLabel7);

        historyCartScoreLabels.add(historyCartScoreLabel1);
        historyCartScoreLabels.add(historyCartScoreLabel2);
        historyCartScoreLabels.add(historyCartScoreLabel3);
        historyCartScoreLabels.add(historyCartScoreLabel4);
        historyCartScoreLabels.add(historyCartScoreLabel5);
        historyCartScoreLabels.add(historyCartScoreLabel6);
        historyCartScoreLabels.add(historyCartScoreLabel7);

        historyRectangles.add(historyRectangle1);
        historyRectangles.add(historyRectangle2);
        historyRectangles.add(historyRectangle3);
        historyRectangles.add(historyRectangle4);
        historyRectangles.add(historyRectangle5);
        historyRectangles.add(historyRectangle6);
        historyRectangles.add(historyRectangle7);

        for(int i=0;i<7;i++){
            try{
                cartDates.add(view.getDateAt(i));
                cartScores.add(view.getCartScoreAt(i));

                historyCartLabels.get(i).setText("carrello del " + cartDates.get(i));
                historyCartScoreLabels.get(i).setText(cartScores.get(i) + "/100");
                updateBackgroundColor(i);
            } catch (IndexOutOfBoundsException e){
                historyCartLabels.get(i).setText("NOT FOUND");
                historyCartScoreLabels.get(i).setText("---");
                updateBackgroundColor(i);
            }

        }

        deleteButton.setOnMouseClicked(mouseEvent -> {
            try {
                onDeleteButton();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                onBackButton();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void onDeleteButton() throws SQLException {
        view.deleteHistory();
    }

    private void updateBackgroundColor(int i) {
        try{
            if(cartScores.get(i) < 30){
                Stop[] stops = new Stop[] {
                        new Stop(0, Color.WHITE),
                        new Stop(1, Color.RED)
                };
                LinearGradient color = new LinearGradient(0.7819905213270142, 1.0, 0.7867298578199052, 1.0, true, CycleMethod.NO_CYCLE, stops);
                historyRectangles.get(i).setFill(color);
            } else if (cartScores.get(i) < 60){
                Stop[] stops = new Stop[] {
                        new Stop(0, Color.WHITE),
                        new Stop(1, Color.YELLOW)
                };
                LinearGradient color = new LinearGradient(0.7819905213270142, 1.0, 0.7867298578199052, 1.0, true, CycleMethod.NO_CYCLE, stops);
                historyRectangles.get(i).setFill(color);
            } else {
                Stop[] stops = new Stop[] {
                        new Stop(0, Color.WHITE),
                        new Stop(1, Color.LIGHTGREEN)
                };
                LinearGradient color = new LinearGradient(0.7819905213270142, 1.0, 0.7867298578199052, 1.0, true, CycleMethod.NO_CYCLE, stops);
                historyRectangles.get(i).setFill(color);
            }
        } catch (IndexOutOfBoundsException e){
            Stop[] stops = new Stop[] {
                    new Stop(0, Color.WHITE),
                    new Stop(1, Color.GRAY)
            };
            LinearGradient color = new LinearGradient(0.7819905213270142, 1.0, 0.7867298578199052, 1.0, true, CycleMethod.NO_CYCLE, stops);
            historyRectangles.get(i).setFill(color);
        }
    }

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}
