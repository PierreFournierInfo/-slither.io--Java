package com.poo.slither.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;

import static com.poo.slither.view.GameView.*;

public class GameMenu extends VBox {

    private final Button onePlayerButton, twoPlayersButton, exitButton;
    private final Slider iasSlider, foodsSlider;

    public GameMenu() {
        super();
        setSpacing(20);
        setPadding(new Insets(20, 20, 20, 20));
        setPrefSize(WIDTH, HEIGHT);

        Image background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/background.jpg")), WIDTH, HEIGHT, false,true);
        setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        onePlayerButton = createButton("Play with One Player");
        twoPlayersButton = createButton("Play with Two Players");
        exitButton = createButton("Exit");

        iasSlider = createSlider(0, 15, 0);
        foodsSlider = createSlider(500, 3000, 1000);

        setAlignment(Pos.CENTER);
        setFillWidth(false);
        getChildren().addAll(onePlayerButton, twoPlayersButton, exitButton, createSliderVbox("Number of IAs",iasSlider), createSliderVbox("Number of food",foodsSlider));
    }

    private VBox createSliderVbox(String label, Slider slider) {
        Label sliderLabel = new Label(label);
        sliderLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        VBox sliderBox = new VBox(5);
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.getChildren().addAll(sliderLabel);
        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(5), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        sliderBox.setBackground(background);

        sliderBox.getChildren().add(slider);
        return sliderBox;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 17px; -fx-font-weight: bold; -fx-background-radius: 20;");        return button;
    }

    private Slider createSlider(double min, double max, double initialValue) {
        Slider slider = new Slider(min, max, initialValue);
        slider.setPrefWidth(200);
        slider.setPrefHeight(40);
        slider.setStyle("-fx-control-inner-background: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit((max - min) / 5);
        slider.setBlockIncrement(1);



        return slider;
    }

    public Button onePlayerButton() {
        return onePlayerButton;
    }

    public Button twoPlayersButton() {
        return twoPlayersButton;
    }

    public Button exitButton() {
        return exitButton;
    }

    public int getNbIas() {
        return (int) iasSlider.getValue();
    }

    public int getNbFood() {
        return (int) foodsSlider.getValue();
    }
}
