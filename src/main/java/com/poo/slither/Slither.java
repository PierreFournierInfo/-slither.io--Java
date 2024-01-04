package com.poo.slither;

import com.poo.slither.controller.SnakeController;
import com.poo.slither.model.Jeu;
import com.poo.slither.model.Serpent;
import com.poo.slither.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Slither extends Application {
    @Override
    public void start(Stage stage) {
        Serpent serpent = new Serpent(100, 100);
        Jeu jeu = new Jeu(15, 1000);
        jeu.addSerpent(serpent);
        GameView gameView = new GameView(jeu);
        Scene scene = new Scene(gameView);
        new SnakeController(serpent, scene);

        stage.setTitle("Slither.io");
        stage.setScene(scene);
        stage.show();

        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if(!jeu.getSerpents().isEmpty()) {
                List<Serpent> dead = jeu.updateGame();
                if(dead.contains(serpent)) {
                    System.out.println("You are dead");
                    stage.close();
                } else {
                    gameView.renderGame();
                }
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
