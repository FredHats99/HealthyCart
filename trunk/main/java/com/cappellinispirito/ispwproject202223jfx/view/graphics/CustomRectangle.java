package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomRectangle extends Pane {

    private static final double WIDTH = 300;
    private static final double HEIGHT = 60;

    public CustomRectangle(String labelText, Image image, String circleLabelText) {

        // create rectangle
        Rectangle rectangle = new Rectangle(WIDTH, HEIGHT, Paint.valueOf("#B1FFC6"));
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);

        // create label on the left
        Label label = new Label(labelText);
        label.setMaxWidth(WIDTH * 0.4);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(Font.font("Calibri"));
        label.setPadding(new Insets(5));

        // create image view in the center
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(WIDTH * 0.6);
        imageView.setFitHeight(HEIGHT * 0.6);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // create circle with label inside on the right
        Circle circle;
        try{
            int score = Integer.parseInt(circleLabelText);
            if(score < 30){
                circle = new Circle(20, Color.RED);
            } else if (score < 60){
                circle = new Circle(20, Color.YELLOW);
            } else {
                circle = new Circle(20, Color.LIGHTGREEN);
            }
        } catch (Exception e){
            circle = new Circle(20, Color.LIGHTGRAY);
        }


        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        Label circleLabel = new Label();
        circleLabel.setText(circleLabelText);
        HBox circleBox = new HBox(circle, circleLabel);
        circleBox.setAlignment(Pos.CENTER_RIGHT);
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(circle, circleLabel);

        // add label, image view, and circle with label inside to an HBox
        HBox hbox = new HBox(label, imageView, stackPane);
        hbox.setSpacing(45);
        hbox.setPrefWidth(WIDTH);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));


        // wrap HBox in a Pane to set padding
        Pane hboxPane = new Pane(hbox);
        hboxPane.setPadding(new Insets(5));

        // add Pane to StackPane
        getChildren().addAll(rectangle, hboxPane);

        // set preferred size of the CustomRectangle
        setPrefSize(WIDTH, HEIGHT);
    }
}
