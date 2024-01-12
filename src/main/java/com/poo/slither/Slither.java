package com.poo.slither;

import com.poo.slither.controller.SnakeController;
import com.poo.slither.model.Jeu;
import com.poo.slither.model.Serpent;
import com.poo.slither.view.GameMenu;
import com.poo.slither.view.GameOverView;
import com.poo.slither.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

import static com.poo.slither.model.Jeu.MAP_HEIGHT;
import static com.poo.slither.model.Jeu.MAP_WIDTH;

public class Slither extends Application {

    @Override
    public void start(Stage stage) {
        showMenu(stage);
    }

    private void showMenu(Stage stage) {
        GameMenu menu = new GameMenu();
        menu.onePlayerButton().setOnAction(e -> run_mode_1_player(stage, new Jeu(menu.getNbIas(), menu.getNbFood())));
        menu.twoPlayersButton().setOnAction(e -> {
            stage.close();
            run_mode_2_players(new Jeu(menu.getNbIas(), menu.getNbFood()));
        });
        menu.exitButton().setOnAction(e -> System.exit(0));
        Scene scene = new Scene(menu);
        stage.setScene(scene);
        stage.setTitle("Slither.io");
        stage.show();
    }

    private void run_mode_1_player(Stage stage, Jeu jeu) {
        Serpent serpent = new Serpent(MAP_WIDTH / 2., MAP_HEIGHT / 2.);
        jeu.addSerpent(serpent);
        GameView gameView = new GameView(jeu, serpent);
        Scene scene = new Scene(gameView);
        new SnakeController(serpent, scene);

        stage.setScene(scene);
        stage.show();

        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if(!jeu.getSerpents().isEmpty()) {
                List<Serpent> dead = jeu.updateGame();
                if(dead.contains(serpent)) {
                    stage.setScene(new Scene(new GameOverView()));
                }
                gameView.renderGame();
            }
        });

        Timeline timeline = new Timeline(k);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void run_mode_2_players(Jeu jeu) {
        Serpent serpent1 = new Serpent(MAP_WIDTH / 2., MAP_HEIGHT / 2.);
        Serpent serpent2 = new Serpent(MAP_WIDTH / 2. + 200, MAP_HEIGHT / 2. + 200);
        jeu.addSerpent(serpent1);
        jeu.addSerpent(serpent2);

        int width = GameView.WIDTH / 2, height = GameView.HEIGHT;
        GameView gameView1 = new GameView(width, height, jeu, serpent1);
        Scene scene1 = new Scene(gameView1);

        GameView gameView2 = new GameView(width, height, jeu, serpent2);
        Scene scene2 = new Scene(gameView2);

        new SnakeController(serpent1, scene1);
        new SnakeController(serpent2, scene2);

        Stage stage = new Stage();
        stage.setTitle("Player 1");
        stage.setScene(scene1);
        stage.setX(0);
        stage.show();

        Stage stage2 = new Stage();
        stage2.setTitle("Player 2");
        stage2.setScene(scene2);
        stage2.setX(stage.getWidth() + stage.getX() + 10);
        stage2.setY(stage.getY());
        stage2.show();

        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if (!jeu.getSerpents().isEmpty()) {
                List<Serpent> dead = jeu.updateGame();
                if(dead.contains(serpent1)) stage.setScene(new Scene(new GameOverView()));
                if(dead.contains(serpent2)) stage2.setScene(new Scene(new GameOverView()));
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
