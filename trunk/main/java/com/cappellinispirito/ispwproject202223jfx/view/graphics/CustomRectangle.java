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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomRectangle extends StackPane {

    private static final double WIDTH = 321;
    private static final double HEIGHT = 80;

    public CustomRectangle(String labelText, Image image, String circleLabelText) {

        // create rectangle
        Rectangle rectangle = new Rectangle(WIDTH, HEIGHT, Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);

        // create label on the left
        Label label = new Label(labelText);
        label.setMaxWidth(WIDTH * 0.4);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(Font.font("Calibri"));

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
                circle = new Circle(20, Color.GREEN);
            }
        } catch (Exception e){
            circle = new Circle(20, Color.GRAY);
        }


        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        Label circleLabel = new Label(circleLabelText);
        circleLabel.setPadding(new Insets(-28));
        HBox circleBox = new HBox(circle, circleLabel);
        circleBox.setAlignment(Pos.CENTER_RIGHT);

        // add label, image view, and circle with label inside to an HBox
        HBox hbox = new HBox(label, imageView, circleBox);
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
