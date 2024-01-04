package com.poo.slither;

import com.poo.slither.controller.SnakeController;
import com.poo.slither.model.Jeu;
import com.poo.slither.model.Serpent;
import com.poo.slither.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Slither extends Application {
    @Override
    public void start(Stage stage) {
        run_mode_1_player(stage);
    }

    private void run_mode_1_player(Stage stage) {
        Serpent serpent = new Serpent(100, 100);
        Jeu jeu = new Jeu(3, 1000);
        jeu.addSerpent(serpent);
        GameView gameView = new GameView(jeu, serpent);
        Scene scene = new Scene(gameView);
        new SnakeController(serpent, scene);

        stage.setTitle("Slither.io");
        stage.setScene(scene);
        stage.show();

        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if(!jeu.getSerpents().isEmpty()) {
                jeu.updateGame();
                gameView.renderGame();
            }
        });

        Timeline timeline = new Timeline(k);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void run_mode_2_players(Stage stage) {
        Jeu jeu = new Jeu(2, 1000);
        Serpent serpent1 = new Serpent(100, 100);
        Serpent serpent2 = new Serpent(300, 300);
        jeu.addSerpent(serpent1);
        jeu.addSerpent(serpent2);

        GameView gameView1 = new GameView(jeu, serpent1);
        Scene scene1 = new Scene(gameView1);

        GameView gameView2 = new GameView(jeu, serpent2);
        Scene scene2 = new Scene(gameView2);

        new SnakeController(serpent1, scene1);
        new SnakeController(serpent2, scene2);

        stage.setTitle("Player 1");
        stage.setScene(scene1);
        stage.setX(0);
        stage.show();

        Stage stage2 = new Stage();
        stage2.setTitle("Player 2");
        stage2.setScene(scene2);
        stage2.setX(stage.getWidth() + stage.getX() + 10);
        stage2.show();

        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if (!jeu.getSerpents().isEmpty()) {
                jeu.updateGame();
                gameView1.renderGame();
                gameView2.renderGame();
            }
        });

        Timeline timeline = new Timeline(k);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
