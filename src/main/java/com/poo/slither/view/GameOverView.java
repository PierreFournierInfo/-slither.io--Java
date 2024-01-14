package com.poo.slither.view;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.poo.slither.view.GameView.*;

public class GameOverView extends VBox {

    public GameOverView() {
        initUI();
    }

    private void initUI() {
        Text gameOverText = new Text("Game Over");
        gameOverText.setStyle("-fx-fill: red; -fx-font-size: 36px; -fx-font-weight: bold;");

        setPrefSize(WIDTH, HEIGHT);
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        getChildren().add(gameOverText);
    }
}

